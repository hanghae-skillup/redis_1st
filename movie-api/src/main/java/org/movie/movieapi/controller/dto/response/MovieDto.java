package org.movie.movieapi.controller.dto.response;

import org.movie.moviedomain.enums.Genre;
import org.movie.moviedomain.enums.Rating;

import java.time.LocalDate;

public class MovieDto {

    public record Response(
            Long id,
            String title,
            Rating rating,
            LocalDate releasedDate,
            Integer runningTimeMinutes,
            Genre genre,
            String thumbnailUrl
    ) {
        public static Response of(
                Long id,
                String title,
                Rating rating,
                LocalDate releasedDate,
                Integer runningTimeMinutes,
                Genre genre,
                String thumbnailUrl
        ) {
            return new Response(id, title, rating, releasedDate, runningTimeMinutes, genre, thumbnailUrl);
        }
    }
}
