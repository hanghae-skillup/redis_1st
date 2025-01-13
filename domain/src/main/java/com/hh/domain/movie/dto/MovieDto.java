package com.hh.domain.movie.dto;

import com.hh.domain.movie.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
@AllArgsConstructor
public class MovieDto {
  private int id;

  private String title;

  private String firmRating;

  private String genre;

  private Date releasedDate;

  private String thumbnail;

  private int runningTime;

  public static MovieDto from(Movie entity) {
    return new MovieDto(
            entity.getId(),
            entity.getTitle(),
            entity.getFirmRating().getFirmRateValue(),
            entity.getGenre().getDisplayName(),
            entity.getReleasedDate(),
            entity.getThumbnail(),
            entity.getRunningTime()
    );
  }


}
