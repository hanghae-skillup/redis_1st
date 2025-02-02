package com.movie.app.controller;
import java.util.List;

import com.google.common.util.concurrent.RateLimiter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.movie.app.domain.Movie;
import com.movie.app.domain.MovieRequestDto;
import com.movie.app.domain.TicketingRequestDto;
import com.movie.app.service.MovieService;

import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class MovieRestController {

    private final MovieService movieService;
    private static RateLimiter moviesRateLimiter;
    private static RateLimiter ticketingLimiter;

    @PostConstruct
    public void init() {
        moviesRateLimiter = RateLimiter.create(0.8);//50permits/60sec = 0.8permits/1sec
        ticketingLimiter = RateLimiter.create(0.003);//1permits/5min = 0.003permits/1sec
    }

    @GetMapping("/api/movies")
    public ResponseEntity<List<Movie>> getMovies(
        @Size(max = 100, message = "title length should not exceed 100 characters")
        @RequestParam(required=false) String title,
        @RequestParam(required=false) String genre) {

            if(!moviesRateLimiter.tryAcquire()) {
                return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
            }
        
            if(genre != null) {
                return ResponseEntity.ok(movieService.getMoviesByGenre(genre));
            } else if (title != null) {
                return ResponseEntity.ok(movieService.getMoviesByTitle(title));
            } else {
                return ResponseEntity.ok(movieService.getMoviesAll());
            }     
    }

    @PostMapping("/api/movies")
    public Movie postMovies(@RequestBody MovieRequestDto requestDto) {
        return movieService.postMovie(requestDto);
    }

    @PutMapping("/api/ticketing/{id}")
    public ResponseEntity<Movie> ticketingMovie(@PathVariable Long id, @RequestBody TicketingRequestDto requestDto) {
        if(!ticketingLimiter.tryAcquire()) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }

        return ResponseEntity.ok(movieService.ticketing(id ,requestDto));
    }
}
