package org.haas.infrastructure.persistence.movie.mapper;

import org.haas.core.domain.movie.Movie;
import org.haas.infrastructure.persistence.movie.entity.MovieEntity;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public Movie toDomain(MovieEntity movieEntity) {
        return Movie.builder()
                .id(movieEntity.getId())
                .title(movieEntity.getTitle())
                .genre(movieEntity.getGenre())
                .runningTime(movieEntity.getRunningTime())
                .director(movieEntity.getDirector())
                .filmRating(movieEntity.getFilmRating())
                .releasedAt(movieEntity.getReleasedAt())
                .thumbnailUrl(movieEntity.getThumbnailUrl())
                .movieStatus(movieEntity.getMovieStatus())
                .build();
    }

    public MovieEntity toEntity(Movie movie) {
        return MovieEntity.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .genre(movie.getGenre())
                .runningTime(movie.getRunningTime())
                .director(movie.getDirector())
                .filmRating(movie.getFilmRating())
                .releasedAt(movie.getReleasedAt())
                .thumbnailUrl(movie.getThumbnailUrl())
                .movieStatus(movie.getMovieStatus())
                .build();
    }
}
