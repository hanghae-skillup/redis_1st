package com.hh.domain.dto;

import com.hh.domain.movie.FirmRating;
import com.hh.domain.movie.Genre;
import com.hh.domain.movie.Movie;
import com.hh.domain.movie.Screen;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class MovieDto {
  private Long id;

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
