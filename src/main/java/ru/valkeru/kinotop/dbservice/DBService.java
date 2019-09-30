package ru.valkeru.kinotop.dbservice;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.valkeru.kinotop.entity.Movie;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBService {
  private final SessionFactory sessionFactory;

  public DBService() {
    sessionFactory = createSessionFactory(getMySqlConfiguration());
    Logger.getLogger("log4j.logger.org.hibernate").setLevel(Level.SEVERE);
  }

  private Configuration getMySqlConfiguration() {
    Configuration configuration = new Configuration();
    configuration.addAnnotatedClass(Movie.class);

    configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
    configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
    configuration.setProperty("hibernate.connection.url", "jdbc:mysql://mysql_kt_java:3306/kinotop");
    configuration.setProperty("hibernate.connection.username", "root");
    configuration.setProperty("hibernate.hbm2ddl.auto", "update");
    configuration.setProperty("hibernate.show_sql", "false");

    return configuration;
  }

  private static SessionFactory createSessionFactory(Configuration configuration) {
    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
    builder.applySettings(configuration.getProperties());
    ServiceRegistry serviceRegistry = builder.build();
    return configuration.buildSessionFactory(serviceRegistry);
  }

  public void saveMovie(Movie movie) {
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    session.save(movie);
    transaction.commit();
    session.close();
  }

  public List<Movie> readMovies() {
    Session session = sessionFactory.openSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<Movie> cq = cb.createQuery(Movie.class);
    Root<Movie> rootEntry = cq.from(Movie.class);
    CriteriaQuery<Movie> all = cq.select(rootEntry);

    return session.createQuery(all).getResultList();
  }
}
