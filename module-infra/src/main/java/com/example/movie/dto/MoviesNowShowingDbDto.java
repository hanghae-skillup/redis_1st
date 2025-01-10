package com.example.movie.dto;

import com.example.entity.movie.Genre;
import com.example.entity.movie.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MoviesNowShowingDbDto {
    private String movieName;
    private Grade grade;
    private LocalDate releaseDate;
    private String thumbnail;
    private Long runningTime;
    private Genre genre;
    private String theaterName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
