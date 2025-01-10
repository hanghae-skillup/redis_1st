package com.movie.moviestorage.movie.mapper;

import com.movie.moviedomain.movie.domain.Movie;
import com.movie.moviestorage.movie.entity.MovieEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MovieMapper {

    public static Movie from(MovieEntity entity) {
        return Movie.of(
                entity.getId(), entity.getTitle(),
                entity.getReleasedAt(), entity.getThumbnailUrl(),
                entity.getRunningTime(), entity.getFilmRating(),
                entity.getGenre()
        );
    }

}
