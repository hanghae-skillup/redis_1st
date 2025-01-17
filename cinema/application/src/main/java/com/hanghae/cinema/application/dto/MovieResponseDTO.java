package com.hanghae.cinema.application.dto;

import com.hanghae.cinema.domain.type.MovieGenre;
import com.hanghae.cinema.domain.type.MovieRating;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public class MovieResponseDTO {
    private Long id;
    private String title;
    private String thumbNail;
    private MovieRating rating;
    private LocalDate releaseAt;
    private Integer runningTime;
    private MovieGenre genre;
}
