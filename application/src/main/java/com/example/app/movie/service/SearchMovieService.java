package com.example.app.movie.service;

import com.example.app.movie.domain.Movie;
import com.example.app.movie.dto.MovieSearchCommand;
import com.example.app.movie.port.LoadMoviePort;
import com.example.app.movie.usecase.SearchMovieUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchMovieService implements SearchMovieUseCase {

    private final LoadMoviePort loadMoviePort;

    @Override
    @Cacheable(value = "movies", key = "#movieSearchCommand")
    public List<Movie> searchMovies(MovieSearchCommand movieSearchCommand) {
        return loadMoviePort.loadAllMovies(movieSearchCommand);
    }
}
