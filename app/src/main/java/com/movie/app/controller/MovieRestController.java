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

    @GetMapping("/api/movies/title")
    @Cacheable(value = "Movies", key = "#title", cacheManager = "contentCacheManager")
    public List<Movie> getMoivesWithTitle(@RequestParam String title) {
        return movieRepository.findByTitle(title);
    }
 
    @GetMapping("/api/movies/genre")
    @Cacheable(value = "Movies", key = "#genre", cacheManager = "contentCacheManager")
    public List<Movie> getMoivesWithGenre(@RequestParam String genre) {
        return movieRepository.findByGenre(genre);
    }

    @PostMapping("/api/movies")
    public Movie postMovies(@RequestBody MovieRequestDto requestDto) {
        Movie movie = new Movie(requestDto);
        movieRepository.save(movie);
        return movie;
    }
}
