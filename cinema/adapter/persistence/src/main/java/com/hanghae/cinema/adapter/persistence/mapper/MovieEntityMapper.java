package com.hanghae.cinema.adapter.persistence.mapper;

import com.hanghae.cinema.adapter.persistence.entity.MovieEntity;
import com.hanghae.cinema.domain.model.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class MovieEntityMapper {
    public Movie toDomain(MovieEntity entity) {
        return Movie.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .thumbNail(entity.getThumbNail())
                .rating(entity.getRating())
                .runningTime(entity.getRunningTime())
                .genre(entity.getGenre())
                .build();
    }

    public List<Movie> toDomainList(List<MovieEntity> entityList) {
        return entityList.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}
