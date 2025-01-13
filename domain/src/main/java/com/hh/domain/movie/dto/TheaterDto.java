package com.hh.domain.movie.dto;

import com.hh.domain.movie.Theater;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TheaterDto {
  private int id;

  private String name;


  public static TheaterDto from(Theater entity) {
    return new TheaterDto(
            entity.getId(),
            entity.getName()
    );
  }
}
