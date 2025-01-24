package com.example.movie.controller;

import com.example.movie.dto.MoviesDetail;
import com.example.movie.service.MovieService;
import com.example.jpa.entity.movie.Genre;
import com.example.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/v1/movies")
    public BaseResponse<List<MoviesDetail>> getMoviesNowShowing(
            @RequestParam(value = "nowShowing") Boolean isNowShowing,
            @RequestParam(value = "genre", required = false)Genre genre,
            @RequestParam(value = "search", required = false)String search
            ) {
        List<MoviesDetail> response = movieService.getMovies(LocalDateTime.now(), isNowShowing, genre, search);
        return new BaseResponse<>(response);
    }

}
