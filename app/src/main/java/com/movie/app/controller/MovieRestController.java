package com.movie.app.controller;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.movie.app.domain.Movie;
import com.movie.app.domain.MovieRequestDto;
import com.movie.app.domain.TicketingRequestDto;
import com.movie.app.service.MovieService;

import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class MovieRestController {

    private final MovieService movieService;

    @GetMapping("/api/movies")
    public List<Movie> getMovies(
        @Size(max = 100, message = "title length should not exceed 100 characters")
        @RequestParam(required=false) String title,
        @RequestParam(required=false) String genre) {
        
            if(genre != null) {
                return movieService.getMoviesByGenre(genre);
            } else if (title != null) {
                return movieService.getMoviesByTitle(title);
            } else {
                return movieService.getMoviesAll();
            }     
    }

    @PostMapping("/api/movies")
    public Movie postMovies(@RequestBody MovieRequestDto requestDto) {
        return movieService.postMovie(requestDto);
    }

    @PutMapping("/api/ticketing/{id}")
    public Movie ticketingMovie(@PathVariable Long id, @RequestBody TicketingRequestDto requestDto) {
        return movieService.ticketing(id ,requestDto);
    }
}
