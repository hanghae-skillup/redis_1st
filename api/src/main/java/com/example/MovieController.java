package com.example;

import com.example.response.MovieResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/movies")
    public List<MovieResponse> getMovies() {
        return movieService.getMovies();
    }
}
