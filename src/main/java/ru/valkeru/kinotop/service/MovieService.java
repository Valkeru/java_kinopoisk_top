package ru.valkeru.kinotop.service;

import ru.valkeru.kinotop.dao.MovieDAO;
import ru.valkeru.kinotop.dao.MovieDAOImpl;
import ru.valkeru.kinotop.entity.Movie;

import java.util.ArrayList;
import java.util.Date;

public class MovieService {
    private MovieDAO movieDAO = new MovieDAOImpl();

    public ArrayList<Movie> findAll() {
        return movieDAO.findAll();
    }

    public void save(Movie movie) {
        movieDAO.save(movie);
    }

    public ArrayList<Movie> getAllByDate(Date date) {
        return movieDAO.findAllByDate(date);
    }

    public ArrayList<Date> getInTopDates() {
        return movieDAO.getInTopDates();
    }

    public ArrayList<Movie> getTodayTop() {
        return movieDAO.getTodayTop();
    }
}
