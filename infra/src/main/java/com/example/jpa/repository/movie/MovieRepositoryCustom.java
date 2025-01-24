package com.example.jpa.repository.movie;

import com.example.jpa.entity.movie.Genre;
import com.example.jpa.repository.movie.dto.MoviesDetailDto;

import java.time.LocalDateTime;
import java.util.List;

public interface MovieRepositoryCustom {
    List<MoviesDetailDto> searchWithFiltering(LocalDateTime now, Genre genre, String title);
}
