package ru.valkeru.kinotop.command;

import org.apache.commons.cli.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.valkeru.kinotop.entity.Movie;
import ru.valkeru.kinotop.service.MovieService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
  private final static String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.102 Safari/537.36 Vivaldi/2.0.1309.42";
  private final static String TOP_URL = "https://www.kinopoisk.ru/top/";

  public static void main(String[] args) {
    Options options = new Options();
    Option dateOption = new Option("d", "date", true, "Date for selection in yyyy-mm-dd format");
    dateOption.setRequired(false);
    options.addOption(dateOption);

    CommandLineParser commandLineParser = new DefaultParser();
    HelpFormatter formatter = new HelpFormatter();
    CommandLine cmd;
    Calendar cal = Calendar.getInstance();

    try {
      cmd = commandLineParser.parse(options, args);
      if (cmd.hasOption("date")) {
        cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(cmd.getOptionValue("date")));
      }
    } catch (ParseException | java.text.ParseException e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
      formatter.printHelp("Kinopoisk parser", options);

      System.exit(1);
    }

    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);

    Date date = cal.getTime();
    MovieService movieService = new MovieService();
    ArrayList<Movie> movies = movieService.getAllByDate(date);

    if (!movies.isEmpty()) {
      Logger.getGlobal().log(Level.WARNING, "We already have rating for this day");
      return;
    }

    StringBuilder builder = new StringBuilder();
    builder.append(TOP_URL).append("day/").append(new SimpleDateFormat("yyyy-MM-dd").format(date)).append("/");
    int TOP_COUNT = 10;

    try {
      Document document = Jsoup.connect(builder.toString()).userAgent(USER_AGENT).get();

      for (int i = 1; i <= TOP_COUNT; i++) {
        String id = String.format("#top250_place_%d", i);
        Elements elements = document.select(id);
        Element child = elements.get(0).child(1);

        Pattern pattern = Pattern.compile("(.+)(\\((\\d+)\\)\\s*)(.*)");
        String text = child.text().trim();
        Matcher matcher = pattern.matcher(text);

        float rating = Float.parseFloat(document.select(id).get(0).child(2).child(0).child(0).text());

        while (matcher.find()) {
          String russianName = matcher.group(1).trim();
          int year = Integer.parseInt(matcher.group(3));
          String originalName = matcher.group(4).trim();
          String votersText = document.select(id).get(0).child(2).child(0).child(1).text();
          int votersCount = Integer.parseInt(
                  votersText.replace("(", "").replace(")", "").replace(" ", "")
          );

          Movie movie = new Movie();
          movie.setRussianName(russianName)
                  .setInTopDate(date)
                  .setYear(year)
                  .setPosition(i)
                  .setRating(rating)
                  .setVotersCount(votersCount);

          if (!russianName.equals(originalName) && !originalName.isEmpty()) {
            movie.setOriginalName(originalName);
          }

          movieService.save(movie);
        }
      }
    } catch (Exception e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
  }
}
