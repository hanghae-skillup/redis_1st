package com.movie.domain.repository;

import com.movie.domain.dto.MovieSearchCondition;
import com.movie.domain.entity.Movie;

import java.util.List;

public interface MovieRepositoryCustom {
    List<Movie> findNowShowingMovies(MovieSearchCondition condition);
} 