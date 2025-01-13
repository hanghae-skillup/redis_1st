package com.example.app.movie.port.in;

import com.example.app.movie.Movie;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface MovieUseCase {
    List<Movie> searchMovies(Predicate predicate);
}
