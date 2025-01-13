package com.hh.domain.movie.dto;

import com.hh.domain.movie.Screen;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScreenDto {

  private int id;

  private int movieId;

  private int theaterId;

  protected LocalDateTime startTime;

  private LocalDateTime endTime;

  public static ScreenDto from(Screen entity) {
    return new ScreenDto(
            entity.getId(),
            entity.getMovieId(),
            entity.getTheaterId(),
            entity.getStartTime(),
            entity.getEndTime()
    );
  }
}
