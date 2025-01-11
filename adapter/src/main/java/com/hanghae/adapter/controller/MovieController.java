package com.hanghae.adapter.controller;

import com.hanghae.application.service.MovieService;
import com.hanghae.common.dto.MovieResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieResponseDto> getMovies() {
        return movieService.getMovies();
    }
}