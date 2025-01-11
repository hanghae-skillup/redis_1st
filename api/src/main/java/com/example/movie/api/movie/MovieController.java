package com.example.movie.api.movie;

import com.example.movie.application.service.movie.MovieService;
import com.example.movie.application.service.movie.dto.MovieResult;
import com.example.movie.api.common.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RequestMapping(value = "/api/movies")
@RestController
public class MovieController {

    private final MovieService movieService;

    @GetMapping(value = "")
    public ApiResponse<List<MovieResult>> searchMovies() {
        return ApiResponse.success(movieService.searchMovies());
    }
}
