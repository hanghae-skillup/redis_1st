package com.example.app.movie.service;

import com.example.app.movie.dto.MovieDto;
import com.example.app.movie.dto.MovieSearchRequest;
import com.example.app.movie.type.MovieStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepoService movieRepoService;

    public List<MovieDto> getMovies(final MovieSearchRequest movieSearchRequest) {
        return movieRepoService.getMovies(movieSearchRequest.showDate(), MovieStatus.SHOWING)
                .stream()
                .map(MovieDto::toDto)
                .toList();
    }
}
