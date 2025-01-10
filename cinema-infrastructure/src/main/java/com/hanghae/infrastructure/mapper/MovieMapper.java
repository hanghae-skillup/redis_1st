package com.hanghae.infrastructure.mapper;

import com.hanghae.domain.model.Movie;
import com.hanghae.infrastructure.entity.MovieEntity;

public class MovieMapper {
    public static Movie toDomain(MovieEntity movieEntity) {
        return new Movie(
                movieEntity.getId(),
                movieEntity.getTitle(),
                movieEntity.getRatingId(),
                movieEntity.getReleaseDate(),
                movieEntity.getRunningTime(),
                movieEntity.getGenreId(),
                UploadFileMapper.toDomain(movieEntity.getUploadFileEntity()),
                movieEntity.getCreatedBy(),
                movieEntity.getCreatedAt(),
                movieEntity.getUpdatedBy(),
                movieEntity.getUpdatedAt()
        );
    }

    public static MovieEntity toEntity(Movie movie) {
        MovieEntity movieEntity = MovieEntity.builder()
                .id(movie.getMovieId())
                .title(movie.getTitle())
                .ratingId(movie.getRatingId())
                .releaseDate(movie.getReleaseDate())
                .runningTime(movie.getRunningTime())
                .genreId(movie.getGenreId())
                .uploadFileEntity(UploadFileMapper.toEntity(movie.getUploadFile()))
                .createdBy(movie.getCreatedBy())
                .createdAt(movie.getCreatedAt())
                .updatedBy(movie.getUpdatedBy())
                .updatedAt(movie.getUpdatedAt())
                .build();
        return movieEntity;
    }
}
