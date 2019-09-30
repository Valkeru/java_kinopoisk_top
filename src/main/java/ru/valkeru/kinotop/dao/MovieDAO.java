package ru.valkeru.kinotop.dao;

import ru.valkeru.kinotop.entity.Movie;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface MovieDAO {
    public void save(Movie movie);

    public ArrayList<Movie> findAll();

    public ArrayList<Movie> findAllByDate(Date date);

    public ArrayList<Movie> getTodayTop();

    public ArrayList<Date> getInTopDates();
}
