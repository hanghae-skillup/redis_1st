package org.haas.infrastructure.persistence.movie.mapper;

import lombok.RequiredArgsConstructor;
import org.haas.core.domain.movie.Movie;
import org.haas.infrastructure.persistence.movie.entity.MovieEntity;
import org.haas.infrastructure.persistence.screening.mapper.ScreeningMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Component
public class MovieMapper {

    private final ScreeningMapper screeningMapper;

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
                .screenings(movieEntity.getScreenings().stream()
                        .map(screeningMapper::toDomain)
                        .collect(toList()))
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
                .screenings(movie.getScreenings().stream()
                        .map(screeningMapper::toEntity)
                        .collect(toList()))
                .build();
    }

}
