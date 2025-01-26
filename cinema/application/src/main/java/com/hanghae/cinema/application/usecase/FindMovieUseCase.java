package com.hanghae.cinema.application.usecase;

import com.hanghae.cinema.application.dto.MovieResponseDTO;
import com.hanghae.cinema.domain.type.MovieGenre;

import java.util.List;


public interface FindMovieUseCase {
    List<MovieResponseDTO> getMovies(String keyword, MovieGenre genre);
}
