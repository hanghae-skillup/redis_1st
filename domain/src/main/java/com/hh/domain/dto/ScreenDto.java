package com.hh.domain.dto;

import com.hh.domain.movie.Movie;
import com.hh.domain.movie.Screen;
import com.hh.domain.movie.Theater;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScreenDto {

  private Long id;

  private MovieDto movie;

  private TheaterDto theater;


  protected LocalDateTime startTime;

  private LocalDateTime endTime;

  public static ScreenDto from(Screen entity) {
    return new ScreenDto(
            entity.getId(),
            MovieDto.from(entity.getMovie()),
            TheaterDto.from(entity.getTheater()),
            entity.getStartTime(),
            entity.getEndTime()
    );
  }
}
