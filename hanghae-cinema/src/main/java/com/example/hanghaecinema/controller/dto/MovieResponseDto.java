package com.example.hanghaecinema.controller.dto;

import com.example.hanghaecinema.domain.Movie;
import com.example.hanghaecinema.domain.Screening;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public record MovieResponseDto(
        String title,
        String ageRating,
        LocalDate releaseDate,
        String thumbnailUrl,
        Integer runtimeMinutes,
        String genre,
        String theaterName,
        LocalDate showDate,
        List<ShowTimeDto> showTimes
) {

    public static MovieResponseDto of(Screening screening) {
        List<ShowTimeDto> showTimeDtoList = screening.getSchedules()
                .stream()
                .map(ShowTimeDto::of)
                .sorted(Comparator.comparing(ShowTimeDto::startTime))
                .toList();
        Movie movie = screening.getMovie();
        return new MovieResponseDto(
                movie.getTitle(),
                movie.getAgeRating().getDisplayName(),
                movie.getReleaseDate(),
                movie.getThumbnailUrl(),
                movie.getRuntimeMinutes(),
                movie.getGenre().getDisplayName(),
                screening.getTheater(),
                screening.getShowDate(),
                showTimeDtoList
        );
    }
}
