package com.hh.domain.dto;

import com.hh.domain.movie.Screen;
import com.hh.domain.movie.Theater;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class TheaterDto {
  private Long id;

  private String name;


  public static TheaterDto from(Theater entity) {
    return new TheaterDto(
            entity.getId(),
            entity.getName()
    );
  }
}
