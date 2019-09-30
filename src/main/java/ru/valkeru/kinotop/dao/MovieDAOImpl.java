package ru.valkeru.kinotop.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.valkeru.kinotop.entity.Movie;
import ru.valkeru.kinotop.utils.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;

public class MovieDAOImpl implements MovieDAO {
    @Override
    public void save(Movie movie) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(movie);
        transaction.commit();
        session.close();
    }

    @Override
    public ArrayList<Movie> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Movie> cq = criteriaBuilder.createQuery(Movie.class);
        Root<Movie> movieRoot = cq.from(Movie.class);
        CriteriaQuery<Movie> all = cq.select(movieRoot);

        return (ArrayList<Movie>) session.createQuery(all).list();
    }

    public Movie get(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        return session.get(Movie.class, id);
    }

    @Override
    public ArrayList<Movie> findAllByDate(Date date) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Query query = session.createQuery("from Movie where inTopDate = :date");
        query.setParameter("date", date);

        return (ArrayList<Movie>) query.list();
    }

    @Override
    public ArrayList<Movie> getTodayTop() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Movie where inTopDate = current_date");

        return (ArrayList<Movie>) query.list();
    }

    @Override
    public ArrayList<Date> getInTopDates() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT DISTINCT m.inTopDate FROM Movie m");

        return (ArrayList<Date>) query.list();
    }
}
