package org.haas.adapter.in.controller;

import lombok.RequiredArgsConstructor;
import org.haas.adapter.in.dto.MovieResponseDto;
import org.haas.adapter.mapper.MovieMapperAdapter;
import org.haas.application.service.MovieService;
import org.haas.core.domain.movie.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;
    private final MovieMapperAdapter movieMapperAdapter;

    @GetMapping("/showing")
    public ResponseEntity<List<MovieResponseDto>> getStatusShowingMovies() {
        List<Movie> movies = movieService.getAllMovieStatusIsShowing();
        List<MovieResponseDto> movieDtos = movies.stream()
                .map(movieMapperAdapter::toDto)
                .collect(toList());
        return ResponseEntity.ok(movieDtos);
    }
}
