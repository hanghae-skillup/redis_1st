package com.hanghae.cinema.adapter.web.controller;


import com.hanghae.cinema.application.dto.MovieResponseDTO;
import com.hanghae.cinema.application.usecase.FindMovieUseCase;
import com.hanghae.cinema.domain.type.MovieGenre;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final FindMovieUseCase findMovieUseCase;

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> getMovie(
            @RequestParam(required = false)
            @Size(max = 255, message = "영화 제목은 최대 255자 이하여야 합니다.")
            String keyword,

            @RequestParam(required = false)
            MovieGenre genre
    ) {
        return ResponseEntity.ok(findMovieUseCase.getMovies(keyword, genre));
    }
}