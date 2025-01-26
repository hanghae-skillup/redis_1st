package com.example.domain.screening.dto;

import com.example.domain.movie.entity.Movie;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class ProjectionScreeningResponseDto {

    private String title;
    private String thumbnailUrl;
    private Movie.Rating rating;
    private LocalDateTime releaseDate;
    private int runtimeMinutes;
    private Movie.Genre genre;
    private String theaterName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
