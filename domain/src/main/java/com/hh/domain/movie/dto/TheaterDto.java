package com.hh.domain.movie.dto;

import com.hh.domain.movie.Theater;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
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
