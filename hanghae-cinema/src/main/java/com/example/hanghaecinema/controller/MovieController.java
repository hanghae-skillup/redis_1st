package com.example.hanghaecinema.controller;

import com.example.hanghaecinema.controller.dto.MovieResponseDto;
import com.example.hanghaecinema.service.ScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/v1/movie")
@RestController
@RequiredArgsConstructor
public class MovieController {

    private final ScreeningService screeningService;

    @GetMapping("/now-showing")
    public List<MovieResponseDto> getAllNowShowingMovies() {
        return screeningService.getNowShowingMovies();
    }
}