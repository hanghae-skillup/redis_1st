package com.movie.api.controller;

import com.movie.application.dto.MovieResponseDto;
import com.movie.application.service.MovieService;
import com.movie.domain.dto.MovieSearchCondition;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/now-showing")
    public List<MovieResponseDto> getNowShowingMovies(@Valid MovieSearchCondition condition) {
        return movieService.getNowShowingMovies(condition);
    }
}
