package com.hanghae.cinema.application.mapper;

import com.hanghae.cinema.application.dto.MovieResponseDTO;
import com.hanghae.cinema.domain.model.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class MovieMapper {

    public MovieResponseDTO toResponseDTO(Movie movie) {
        return MovieResponseDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .thumbNail(movie.getThumbNail())
                .rating(movie.getRating())
                .releaseAt(movie.getReleaseAt())
                .runningTime(movie.getRunningTime())
                .genre(movie.getGenre())
                .build();
    }

    public List<MovieResponseDTO> toResponseDTOList(List<Movie> movieList) {
        return movieList.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
