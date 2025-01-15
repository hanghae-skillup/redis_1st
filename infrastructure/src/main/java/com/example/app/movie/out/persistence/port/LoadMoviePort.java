package com.example.app.movie.out.persistence.port;

import com.example.app.movie.domain.Movie;
import com.example.app.movie.dto.request.MovieSearchRequest;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface LoadMoviePort {
    List<Movie> loadAllMovies(MovieSearchRequest movieSearchRequest);
}
