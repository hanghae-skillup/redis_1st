package com.example.movie.response;

import com.example.repository.movie.dto.MovieDto;
import lombok.Getter;

import java.util.List;

@Getter
public class MoviesServiceResponse {
    public static List<MovieResponse> of(List<MovieDto> movies) {
        return movies.stream()
                .map(MovieResponse::new)
                .toList();
    }
}
