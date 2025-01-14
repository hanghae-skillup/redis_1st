package com.example.app.movie.out.persistence.port;

import com.example.app.movie.Movie;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface LoadMoviePort {
    List<Movie> loadAllMovies(Predicate predicate);
}
