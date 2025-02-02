package com.example.movie.presentation.controller;

import com.example.movie.application.dto.MoviesNowShowingDetail;
import com.example.movie.application.service.MovieService;
import com.example.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/v1/movies/now-showing")
    public BaseResponse<List<MoviesNowShowingDetail>> getMoviesNowShowing() {
        List<MoviesNowShowingDetail> response = movieService.getNowShowingMovies(LocalDateTime.now());
        return new BaseResponse<>(response);
    }

}
