package com.hanghae.cinema.adapter.persistence.repository;

import com.hanghae.cinema.adapter.persistence.entity.MovieEntity;
import com.hanghae.cinema.application.dto.MovieResponseDTO;
import com.hanghae.cinema.domain.type.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity, Long>, MovieRepositoryCustom {

    List<MovieResponseDTO> findMovie(LocalDate date, LocalDateTime dateTime, String keyword, MovieGenre genre);
}