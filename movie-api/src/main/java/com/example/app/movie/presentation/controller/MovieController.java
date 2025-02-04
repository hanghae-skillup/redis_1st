package com.example.app.movie.presentation.controller;

import com.example.app.common.annotation.ClientIp;
import com.example.app.movie.presentation.dto.request.MovieSearchRequest;
import com.example.app.movie.presentation.dto.response.MovieResponse;
import com.example.app.movie.presentation.service.RateLimitService;
import com.example.app.movie.presentation.service.RedisRateLimitService;
import com.example.app.movie.usecase.SearchMovieUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class MovieController {

    private final SearchMovieUseCase searchMovieUseCase;
    private final RateLimitService rateLimitService;
    private final RedisRateLimitService redisRateLimitService;

    @GetMapping("/movies")
    public ResponseEntity<List<MovieResponse>> searchMovies(
            @Valid MovieSearchRequest movieSearchRequest,
            @ClientIp String clientIp) {

        redisRateLimitService.checkAccessLimit(clientIp);

        var data = searchMovieUseCase.searchMovies(movieSearchRequest.toMovieSearchCommand())
                .stream()
                .map(MovieResponse::toResponse)
                .toList();

        return ResponseEntity.ok(data);
    }
}
