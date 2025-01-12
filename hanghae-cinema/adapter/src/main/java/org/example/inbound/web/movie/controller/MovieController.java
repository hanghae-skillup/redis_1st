package org.example.inbound.web.movie.controller;

import lombok.RequiredArgsConstructor;
import org.example.port.inbound.ScreeningUseCase;
import org.example.port.inbound.dto.MovieResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/v1/movie")
@RestController
@RequiredArgsConstructor
public class MovieController {

    private final ScreeningUseCase screeningUseCase;

    @GetMapping("/now-showing")
    public List<MovieResponseDto> getAllNowShowingMovies() {
        return screeningUseCase.getNowShowingMovies();
    }
}