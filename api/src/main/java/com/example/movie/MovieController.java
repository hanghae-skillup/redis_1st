package com.example.movie;

import com.example.movie.request.MovieSearchRequest;
import com.example.movie.response.MovieResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/movies")
    public List<MovieResponse> getMovies(MovieSearchRequest request) {
        return movieService.getMovies(request.toServiceRequest());
    }
}
