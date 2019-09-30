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

  @Column(nullable = false, name = "russian_name")
  private String russianName;

  @Column(name = "original_name")
  private String originalName;

  @Column(nullable = false)
  private int year;

  @Column(nullable = false)
  private float rating;

  @Column(nullable = false, name = "voters_count")
  private int votersCount;

  @Column(nullable = false, name = "in_top_date")
  private Date inTopDate;

  public Movie() {
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
