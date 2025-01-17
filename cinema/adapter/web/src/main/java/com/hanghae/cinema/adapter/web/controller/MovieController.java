package com.hanghae.cinema.adapter.web.controller;


import com.hanghae.cinema.application.dto.MovieResponseDTO;
import com.hanghae.cinema.application.usecase.FindMovieUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final FindMovieUseCase findMovieUseCase;

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> getReleasedMovieList() {
        return ResponseEntity.ok(findMovieUseCase.getMovies());
    }
}