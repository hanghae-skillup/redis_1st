package com.example.app.movie.presentation.controller;

import com.example.app.movie.presentation.dto.request.MovieSearchRequest;
import com.example.app.movie.presentation.dto.response.MovieResponse;
import com.example.app.movie.usecase.SearchMovieUseCase;
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

    private final SearchMovieUseCase searchMovieUseCase;

    @GetMapping("/movies")
    public ResponseEntity<List<MovieResponse>> getMovies(@Valid MovieSearchRequest movieSearchRequest) {
        var data = searchMovieUseCase.searchMovies(MovieSearchRequest.toMovieSearchCommand(movieSearchRequest))
                .stream()
                .map(MovieResponse::toResponse)
                .toList();

        return ResponseEntity.ok(data);
    }
}
