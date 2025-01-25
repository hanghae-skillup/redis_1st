package com.example.movie.dto;

import com.example.jpa.entity.movie.Genre;
import com.example.jpa.entity.movie.Grade;

import java.time.LocalDate;
import java.util.List;

public record MoviesDetailResponse(
        Long movieId,
        String movieName,
        Grade grade,
        LocalDate releaseDate,
        String thumbnail,
        Long runningTime,
        Genre genre,
        List<ScreeningsDetail> screeningsDetails
) {
}
