package com.example.movie.response;

import com.example.entity.movie.Genre;
import com.example.entity.movie.Grade;

import java.time.LocalDate;
import java.util.List;

public record MoviesNowShowingDetail (
        String movieName,
        Grade grade,
        LocalDate releaseDate,
        String thumbnail,
        Long runningTime,
        Genre genre,
        List<ScreeningsDetail> screeningsDetailList
) {
}
