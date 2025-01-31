package com.movie.domain.movie.dto.command;

import com.movie.common.enums.FilmRating;
import com.movie.common.enums.Genre;

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

    public record Search(String title, Genre genre) {
        public static Search of(String title, Genre genre) {
            return new Search(title, genre);
        }
    }

}
