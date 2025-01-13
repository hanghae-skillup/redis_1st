package com.example.app.movie.out.persistence.adapter;

import com.example.app.movie.Movie;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface MoviePort {
    List<Movie> findAllBy(Predicate predicate);
}
