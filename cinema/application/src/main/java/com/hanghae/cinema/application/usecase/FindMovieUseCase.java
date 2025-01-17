package com.hanghae.cinema.application.usecase;

import com.hanghae.cinema.application.dto.MovieResponseDTO;

import java.util.List;


public interface FindMovieUseCase {
    List<MovieResponseDTO> getMovies();
}
