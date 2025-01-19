package com.movie.domain.repository;

import com.movie.domain.entity.Movie;
import java.util.List;
import java.util.Optional;

public interface MovieRepository {
    Movie save(Movie movie);
    Optional<Movie> findById(Long id);
    List<Movie> findAll();
}