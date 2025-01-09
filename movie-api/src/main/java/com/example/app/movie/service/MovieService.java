package com.example.app.movie.service;

import com.example.app.movie.dto.MovieDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepoService movieRepoService;

    public List<MovieDto> getMovies() {
        return movieRepoService.getMovies()
                .stream()
                .map(MovieDto::toDto)
                .toList();
    }
}
