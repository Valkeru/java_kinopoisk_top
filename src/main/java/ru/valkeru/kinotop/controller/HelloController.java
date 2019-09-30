package ru.valkeru.kinotop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.valkeru.kinotop.dbservice.DBService;
import ru.valkeru.kinotop.entity.Movie;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String getIndex(Model model) {
    DBService dbService = new DBService();
    List<Movie> movies = dbService.readMovies();
    model.addAttribute("movies", movies);

    return "index";
  }
}
