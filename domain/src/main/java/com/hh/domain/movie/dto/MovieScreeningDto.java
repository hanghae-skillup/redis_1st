package com.hh.domain.movie.dto;

import com.hh.domain.movie.FirmRating;
import com.hh.domain.movie.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import java.time.LocalDateTime;
import java.util.List;


@ToString
@Getter
@AllArgsConstructor
public class MovieScreeningDto {

  private Integer movieId;
  private String title;
  private FirmRating firmRating;
  private LocalDateTime releasedDate;
  private String thumbnail;
  private int runningTime;
  private Genre genre;
  private List<ScreenDto> screenDtos;
}
