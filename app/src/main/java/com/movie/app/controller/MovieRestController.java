package com.movie.app.controller;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import com.movie.app.domain.Movie;
import com.movie.app.domain.MovieRepository;
import com.movie.app.domain.MovieRequestDto;

import lombok.RequiredArgsConstructor;


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

    @GetMapping("/api/search")
    @Cacheable(value = "Movies", key = "#title", cacheManager = "contentCacheManager")
    public List<Movie> searchMoivesWithTitle(@RequestParam String title) {
        return movieRepository.findByTitle(title);
    }
}
