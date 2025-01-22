package com.example.movie.application.dto;

import com.example.movie.entity.movie.Genre;
import com.example.movie.entity.movie.Grade;

import java.time.LocalDate;
import java.util.List;

public record MoviesDetail(
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
