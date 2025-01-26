package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.MoviesFilterRequestDto;
import org.example.dto.response.PlayingMoviesResponseDto;
import org.example.service.movie.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/movies/playing")
    public ResponseEntity<List<PlayingMoviesResponseDto>> getPlayingMovies(@ModelAttribute @Validated MoviesFilterRequestDto moviesFilterRequestDto) {
        List<PlayingMoviesResponseDto> playingMovies = movieService.getPlayingMovies(moviesFilterRequestDto);
        return ResponseEntity.ok(playingMovies);
    }
}
