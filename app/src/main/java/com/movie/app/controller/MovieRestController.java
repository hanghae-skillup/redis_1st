package com.movie.app.controller;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.movie.app.domain.Movie;
import com.movie.app.domain.MovieRepository;
import com.movie.app.domain.MovieRequestDto;

import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class MovieRestController {

    private final MovieRepository movieRepository;

    @GetMapping("/api/movies")
    public List<Movie> getMovies(
        @Size(max = 100, message = "title length should not exceed 100 characters")
        @RequestParam(required=false) String title,
        @RequestParam(required=false) String genre) {
        
            if(genre != null) {
                return movieRepository.findByGenre(genre);
            } else if (title != null) {
                return movieRepository.findByTitle(title);
            } else {
                return movieRepository.findAll();
            }     
    }

    @PostMapping("/api/movies")
    public Movie postMovies(@RequestBody MovieRequestDto requestDto) {
        Movie movie = new Movie(requestDto);
        movieRepository.save(movie);
        return movie;
    }
}
