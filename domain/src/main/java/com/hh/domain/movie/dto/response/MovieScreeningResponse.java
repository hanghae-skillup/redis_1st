package com.hh.domain.movie.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hh.domain.movie.FirmRating;
import com.hh.domain.movie.Genre;
import com.hh.domain.movie.dto.MovieScreeningDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@AllArgsConstructor
public class MovieScreeningResponse {
  private int movieId;
  private String title;
  private FirmRating firmRating;
  private String releasedDate;
  private String thumbnail;
  private int runningTime;
  private Genre genre;
  private List<ScreenResponse> movieScreeningDtos;

  public static MovieScreeningResponse from(MovieScreeningDto dto) {
    return new MovieScreeningResponse(
            dto.getMovieId(),
            dto.getTitle(),
            dto.getFirmRating(),
            dto.getReleasedDate(),
            dto.getThumbnail(),
            dto.getRunningTime(),
            dto.getGenre(),
            dto.getScreenDtos().stream().map(ScreenResponse::from).toList()
    );
  }
}
