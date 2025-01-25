package com.cinema.adapter.controller;

import com.cinema.application.dto.MovieRequestDTO;
import com.cinema.application.dto.MovieResponseDTO;
import com.cinema.application.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;

    // TODO : 상영일자가 추가되는 경우, bookable 쿼리파라미터를 추가하여 상영가능한 상태의 영화만 조회할 수 있도록 확장 가능
    @GetMapping
    public List<MovieResponseDTO> getMovieScreenings(@Valid @RequestBody MovieRequestDTO movieRequestDTO) {
        return movieService.getMovieScreenings(movieRequestDTO);
    }

    @GetMapping("/evictRedisCache")
    public void evictRedisCache() {
        movieService.evictShowingMovieCache();
    }
}

