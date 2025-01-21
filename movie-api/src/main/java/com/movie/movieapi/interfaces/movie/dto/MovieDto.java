package com.movie.movieapi.interfaces.movie.dto;

import com.movie.common.enums.FilmRating;
import com.movie.common.enums.Genre;
import com.movie.domain.movie.dto.info.MovieInfo;

import java.time.LocalDateTime;

public class MovieDto {

    public record Response(
            Long id, String title,
            LocalDateTime releaseDate,
            String thumbnailUrl, String runningTime,
            FilmRating filmRating, Genre genre
    ) {
        public static Response of(
                Long id, String title,
                LocalDateTime releaseDate,
                String thumbnailUrl, String runningTime,
                FilmRating filmRating, Genre genre
        ) {
            return new Response(id, title, releaseDate, thumbnailUrl, runningTime, filmRating, genre);
        }

        public static Response from(MovieInfo.Get info) {
            return Response.of(
                    info.id(), info.title(), info.releaseDate(), info.thumbnailUrl(),
                    info.runningTime(), info.filmRating(), info.genre()
            );
        }
    }

}
