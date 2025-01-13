package com.hh.domain.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class MovieScreeningDto {

  private String title;
  private String firmRating;
  private Date releasedDate;
  private String theaterName;
  private String thumbnail;
  private int runningTime;
  private String genre;
  private String startTime;
  private String endTime;
}
