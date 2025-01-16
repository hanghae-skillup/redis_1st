package com.movie.storage.movie.mapper;

import com.movie.domain.movie.domain.Movie;
import com.movie.storage.movie.entity.MovieEntity;
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
