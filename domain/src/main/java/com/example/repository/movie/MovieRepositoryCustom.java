package com.example.repository.movie;

import com.example.entity.Movie;

import java.util.List;

public interface MovieRepositoryCustom {
    List<Movie> findAllByTitleAndGenre(String title, String genre);
}
