package com.movie.api.controller;

import com.movie.application.dto.MovieResponseDto;
import com.movie.application.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/api/v1/movies/now-showing")
    public List<MovieResponseDto> getNowShowingMovies() {
        return movieService.getNowShowingMovies();
    }
}
