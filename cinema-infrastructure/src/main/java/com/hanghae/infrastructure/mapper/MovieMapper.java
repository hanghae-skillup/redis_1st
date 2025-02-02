package com.hanghae.infrastructure.mapper;

import com.hanghae.domain.model.Movie;
import com.hanghae.infrastructure.entity.MovieEntity;

public class MovieMapper {
    public static Movie toDomain(MovieEntity movieEntity) {
        return new Movie(
                movieEntity.getId(),
                movieEntity.getTitle(),
                movieEntity.getRating(),
                movieEntity.getReleaseDate(),
                movieEntity.getRunningTimeMinutes(),
                movieEntity.getGenre(),
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
                .rating(movie.getRating())
                .releaseDate(movie.getReleaseDate())
                .runningTimeMinutes(movie.getRunningTimeMinutes())
                .genre(movie.getGenre())
                .uploadFileEntity(UploadFileMapper.toEntity(movie.getUploadFile()))
                .createdBy(movie.getCreatedBy())
                .updatedBy(movie.getUpdatedBy())
                .build();
        return movieEntity;
    }
}
