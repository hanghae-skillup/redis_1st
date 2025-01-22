package com.example.app.movie.domain;

import com.example.app.movie.type.MovieGenre;
import com.example.app.movie.type.MovieRating;
import com.example.app.movie.type.MovieStatus;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record Movie(
        Long id,
        String title,
        String description,
        MovieStatus status,
        MovieRating rating,
        MovieGenre genre,
        String thumbnail,
        int runningTime,
        LocalDate releaseDate,
        List<Showtime> showtimes,
        List<Theater> theaters
){

}
