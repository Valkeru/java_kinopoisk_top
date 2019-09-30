package ru.valkeru.kinotop.command;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.valkeru.kinotop.dbservice.DBService;
import ru.valkeru.kinotop.entity.Movie;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PageParser {

  void parse() {
    try {
      String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.102 Safari/537.36 Vivaldi/2.0.1309.42";
      String TOP_URL = "https://www.kinopoisk.ru/top/";
      int TOP_COUNT = 10;

      Document document = Jsoup.connect(TOP_URL).userAgent(USER_AGENT).get();
      DBService dbService = new DBService();

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
                  .setYear(year)
                  .setPosition(i)
                  .setRating(rating)
                  .setVotersCount(votersCount);

          if (!russianName.equals(originalName) && !originalName.isEmpty()) {
            movie.setOriginalName(originalName);
          }

          dbService.saveMovie(movie);
        }
      }
    } catch (Exception e) {
      Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage());
    }
  }
}
