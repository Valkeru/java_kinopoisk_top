package ru.valkeru.kinotop.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.valkeru.kinotop.entity.Movie;
import ru.valkeru.kinotop.service.MovieService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class HelloController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(Model model) {
        MovieService movieService = new MovieService();
        ArrayList<Movie> movies = movieService.findAll();

        model.addAttribute("movies", movies);

        return "index";
    }

    @RequestMapping(value = "/datetop", method = RequestMethod.GET)
    public String getByDate(
            @Nullable @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam Date date,
            Model model
    ) {
        if (date == null) {
            return "redirect:/";
        }

        MovieService movieService = new MovieService();
        ArrayList<Movie> movies = movieService.getAllByDate(date);

        model.addAttribute("movies", movies);
        model.addAttribute("date", date);
        model.addAttribute("inTopDates", movieService.getInTopDates());

        return "index";
    }
}
