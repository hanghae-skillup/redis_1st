package org.haas.adapter.in.controller;

import lombok.RequiredArgsConstructor;
import org.haas.application.service.MovieService;
import org.haas.core.domain.movie.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/showing")
    public ResponseEntity<List<MovieDto>> getStatusShowingMovies() {
        List<Movie> movies = movieService.getAllMovieStatusIsShowing();
        return ResponseEntity.ok(movies);
    }
}
