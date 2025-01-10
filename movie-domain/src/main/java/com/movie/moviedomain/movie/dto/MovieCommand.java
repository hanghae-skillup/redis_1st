package com.movie.moviedomain.movie.dto;

import com.movie.moviedomain.enums.FilmRating;
import com.movie.moviedomain.enums.Genre;

import java.time.LocalDateTime;

public class MovieCommand {

    public record Get(
            Long id,
            String Title,
            LocalDateTime releasedAt,
            String thumbnailUrl,
            String runningTime,
            FilmRating filmRating,
            Genre genre
    ) {
        public static Get of(Long id, String title, LocalDateTime releasedAt,
                             String thumbnailUrl, String runningTime,
                             FilmRating filmRating, Genre genre) {
            return new Get(id, title, releasedAt, thumbnailUrl, runningTime, filmRating, genre);
        }
    }

}
