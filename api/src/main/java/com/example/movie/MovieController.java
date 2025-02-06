package com.example.movie;

import com.example.aop.MovieSearchRateLimited;
import com.example.movie.request.MovieSearchRequest;
import com.example.movie.response.MovieResponse;
import com.example.response.ApiResponse;
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

    @GetMapping("/v1/movies")
    @MovieSearchRateLimited
    public ApiResponse<List<MovieResponse>> getMovies(MovieSearchRequest request) {
        return ApiResponse.ok("영화 목록 조회",movieService.getMovies(request.toServiceRequest()));
    }
}
