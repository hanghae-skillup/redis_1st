package com.hanghae.cinema.adapter.persistence.repository;

import com.hanghae.cinema.application.dto.MovieResponseDTO;
import com.hanghae.cinema.domain.type.MovieGenre;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MovieRepositoryCustom {
    List<MovieResponseDTO> findMovie(LocalDate date, LocalDateTime dateTime, String keyword, MovieGenre movieGenre);
}
