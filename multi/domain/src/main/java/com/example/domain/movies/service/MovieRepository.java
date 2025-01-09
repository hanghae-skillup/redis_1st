package com.example.domain.movies.service;

import com.example.domain.movies.entity.Movie;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository {

    public Movie getMovieInfo(Long movieId);

}
