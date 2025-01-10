package com.example.response;

import com.example.entity.Movie;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MoviesServiceResponse {
    public static List<MovieResponse> of(List<Movie> movies) {
        List<MovieResponse> a = new ArrayList<>();
        for (Movie movie: movies) {
            a.add(new MovieResponse(movie));
        }
        return a;
    }
}
