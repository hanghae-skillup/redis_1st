package com.example.movie.presentation.controller;

import com.example.movie.application.dto.MoviesNowShowingDetail;
import com.example.movie.application.service.MovieService;
import com.example.movie.entity.movie.Genre;
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

    @GetMapping("/v1/movies/now-showing")
    public BaseResponse<List<MoviesNowShowingDetail>> getMoviesNowShowing(
            @RequestParam(value = "genre", required = false)Genre genre,
            @RequestParam(value = "search", required = false)String search
            ) {
        List<MoviesNowShowingDetail> response = movieService.getMoviesNowShowing(LocalDateTime.now(), genre, search);
        return new BaseResponse<>(response);
    }

}
