package com.movie.app.controller;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.movie.app.domain.Movie;
import com.movie.app.domain.MovieRepository;
import com.movie.app.domain.MovieRequestDto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@RestController
public class MovieRestController {

    private final MovieRepository movieRepository;

    @GetMapping("/api/movies")
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @PostMapping("/api/movies")
    public Movie postMovies(@RequestBody MovieRequestDto requestDto) {
        Movie movie = new Movie(requestDto);
        movieRepository.save(movie);
        return movie;
    }
}
