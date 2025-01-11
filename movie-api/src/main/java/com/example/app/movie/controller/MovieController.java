package com.example.app.movie.controller;

import com.example.app.movie.dto.MovieDto;
import com.example.app.movie.dto.MovieSearchRequest;
import com.example.app.movie.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<List<MovieDto>> getMovies(@Valid MovieSearchRequest movieSearchRequest) {
        return ResponseEntity.ok(movieService.getMovies(movieSearchRequest));
    }
}
