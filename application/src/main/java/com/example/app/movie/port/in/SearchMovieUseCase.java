package com.example.app.movie.port;

import com.example.app.movie.Movie;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface SearchMovieUseCase {
    List<Movie> searchMovies(Predicate predicate);
}
