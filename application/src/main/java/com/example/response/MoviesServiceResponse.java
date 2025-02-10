package com.example.response;

import com.example.entity.Movie;
import lombok.Getter;

import java.util.List;

@Getter
public class MoviesServiceResponse {
    public static List<MovieResponse> of(List<Movie> movies) {
        return movies.stream()
                .map(MovieResponse::new)
                .toList();
    }
}
