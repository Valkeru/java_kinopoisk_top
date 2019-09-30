package ru.valkeru.kinotop.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Entity
@Table(name = "movie")
public class Movie implements Serializable {

  @Column
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private int position;

  @Column(nullable = false)
  private String russianName;

  @Column
  private String originalName;

  @Column(nullable = false)
  private int year;

  @Column(nullable = false)
  private float rating;

  @Column(nullable = false)
  private int votersCount;

  @Column(nullable = false)
  private Date inTopDate;

  public Movie() {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    calendar.set(Calendar.ZONE_OFFSET, 0);

    inTopDate = calendar.getTime();
  }

  public int getId() {
    return id;
  }

  public Movie setId(int id) {
    this.id = id;
    return this;
  }

  public int getPosition() {
    return position;
  }

  public Movie setPosition(int position) {
    this.position = position;
    return this;
  }

  public String getRussianName() {
    return russianName;
  }

  public Movie setRussianName(String russianName) {
    this.russianName = russianName;
    return this;
  }

  public String getOriginalName() {
    return originalName;
  }

  public Movie setOriginalName(String originalName) {
    this.originalName = originalName;
    return this;
  }

  public int getYear() {
    return year;
  }

  public Movie setYear(int year) {
    this.year = year;
    return this;
  }

  public float getRating() {
    return rating;
  }

  public Movie setRating(float rating) {
    this.rating = rating;
    return this;
  }

  public int getVotersCount() {
    return votersCount;
  }

  public Movie setVotersCount(int votersCount) {
    this.votersCount = votersCount;
    return this;
  }

  public Date getInTopDate() {
    return inTopDate;
  }

  public Movie setInTopDate(Date inTopDate) {
    this.inTopDate = inTopDate;
    return this;
  }
}
