package com.hh.domain.movie.dto;

import com.hh.domain.movie.Screen;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScreenDto {

  private Integer id;

  private String name;

  private Integer movieId;

  private Integer theaterId;

  protected String startTime;

  private String endTime;

  public ScreenDto(String name,String startTime, String endTime) {
    this.name = name;
    this.startTime = startTime;
    this.endTime = endTime;
  }
  public static ScreenDto from(Screen entity) {
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    return new ScreenDto(
            entity.getId(),
            entity.getName(),
            entity.getMovieId(),
            entity.getTheaterId(),
            entity.getStartTime().format(timeFormatter),
            entity.getEndTime().format(timeFormatter)
    );
  }
}
