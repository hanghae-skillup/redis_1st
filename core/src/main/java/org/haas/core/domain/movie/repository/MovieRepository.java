package org.haas.core.domain.movie.repository;

import org.haas.core.domain.movie.Movie;

import java.util.List;

public interface MovieRepository {

    Movie findById(Long id);
    Movie save(Movie movie);

    List<Movie> findAllStatusShowingMovies();

    List<Movie> findAllByMovieStatusOrderByReleasedAtDesc();
}
