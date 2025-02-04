package com.movie.api.controller;

import com.movie.api.response.ApiResponse;
import com.movie.domain.entity.Movie;
import com.movie.domain.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/current")
    public ApiResponse<List<Movie>> getCurrentMovies() {
        return ApiResponse.success(movieService.getCurrentMovies());
    }

    @GetMapping("/upcoming")
    public ApiResponse<List<Movie>> getUpcomingMovies() {
        return ApiResponse.success(movieService.getUpcomingMovies());
    }
}
