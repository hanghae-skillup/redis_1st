package com.example.jpa.repository.movie.dto;

import com.example.jpa.entity.movie.Genre;
import com.example.jpa.entity.movie.Grade;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MoviesDetailDto {
    private Long movieId;
    private String movieName;
    private Grade grade;
    private LocalDate releaseDate;
    private String thumbnail;
    private Long runningTime;
    private Genre genre;
    private Long theaterId;
    private String theaterName;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    @QueryProjection
    public MoviesDetailDto(Long movieId, String movieName, Grade grade, LocalDate releaseDate, String thumbnail, Long runningTime, Genre genre, Long theaterId, String theaterName, LocalDateTime startAt, LocalDateTime endAt) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.grade = grade;
        this.releaseDate = releaseDate;
        this.thumbnail = thumbnail;
        this.runningTime = runningTime;
        this.genre = genre;
        this.theaterId = theaterId;
        this.theaterName = theaterName;
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
