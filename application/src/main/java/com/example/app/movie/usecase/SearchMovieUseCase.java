package com.example.app.movie.usecase;

import com.example.app.movie.domain.Movie;
import com.example.app.movie.dto.SearchMovieCommand;

import java.util.List;

public interface SearchMovieUseCase {
    List<Movie> searchMovies(SearchMovieCommand searchMovieCommand);
}
