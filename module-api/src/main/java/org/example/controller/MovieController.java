package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.response.PlayingMoviesResponseDto;
import org.example.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/movies/playing")
    public ResponseEntity<List<PlayingMoviesResponseDto>> getPlayingMovies() {
        List<PlayingMoviesResponseDto> playingMovies = movieService.getPlayingMovies();
        return ResponseEntity.ok(playingMovies);
    }
}
