package com.example.movie;

import com.example.MovieService;
import com.example.movie.request.MovieSearchRequest;
import com.example.response.MovieResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/movies")
    public List<MovieResponse> getMovies(@ModelAttribute MovieSearchRequest request) {
        request.validate();
        return movieService.getMovies();
    }
}
