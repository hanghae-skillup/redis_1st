package com.movie.api.controller;

import com.movie.application.dto.MovieResponseDto;
import com.movie.application.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping(value = "/api/v1/movies/now-showing", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public List<MovieResponseDto> getNowShowingMovies() {
        return movieService.getNowShowingMovies();
    }
}
