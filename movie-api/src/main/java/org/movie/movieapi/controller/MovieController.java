package org.movie.movieapi.controller;

import org.movie.movieapi.common.response.ApiResponse;
import org.movie.movieapi.controller.dto.response.MovieDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @GetMapping
    public ApiResponse<List<MovieDto.Response>> getNowPlayingMovies() {
        List<MovieDto.Response> movies = movieService.;

        return ApiResponse.success(movies);
    }
}
