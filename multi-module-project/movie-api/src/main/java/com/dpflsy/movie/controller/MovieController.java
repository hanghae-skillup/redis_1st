package com.dpflsy.movie.controller;

import com.dpflsy.common.dto.MovieResponse;
import com.dpflsy.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/api/movies/now-playing")
    public List<MovieResponse> getNowPlayingMovies() {
        return movieService.getNowPlayingMovies();
    }
}