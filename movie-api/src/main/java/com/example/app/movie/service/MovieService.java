package com.example.app.movie.service;

import com.example.app.movie.dto.MovieDto;
import com.example.app.movie.dto.SearchMovies;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepoService movieRepoService;

    public List<MovieDto> getMovies(final SearchMovies searchMovies) {
        return movieRepoService.getMovies(searchMovies.showDate())
                .stream()
                .map(MovieDto::toDto)
                .toList();
    }
}
