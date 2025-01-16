package com.movie.moviedomain.movie.dto.info;

import com.movie.moviedomain.enums.FilmRating;
import com.movie.moviedomain.enums.Genre;
import com.movie.moviedomain.movie.domain.Movie;

import java.time.LocalDateTime;

public class MovieInfo {

    public record Get(
        Long id, String title,
        LocalDateTime releaseDate,
        String thumbnailUrl, String runningTime,
        FilmRating filmRating, Genre genre
    ) {
        public static Get of(Long id, String title,
                             LocalDateTime releaseDate,
                             String thumbnailUrl, String runningTime,
                             FilmRating filmRating, Genre genre) {
            return new Get(id, title, releaseDate, thumbnailUrl, runningTime, filmRating, genre);
        }

        public static Get from(Movie movie) {
            return Get.of(
                    movie.getId(), movie.getTitle(),
                    movie.getReleasedAt(), movie.getThumbnailUrl(),
                    movie.getRunningTime(), movie.getFilmRating(),
                    movie.getGenre()
            );
        }
    }

}
