package com.example.repository.movie;

import com.example.repository.movie.dto.MovieDto;

import java.util.List;

public interface MovieRepositoryCustom {
    List<MovieDto> findAllByTitleAndGenre(String title, String genre);
}
