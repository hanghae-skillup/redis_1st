package com.example.app.movie.service;

import com.example.app.movie.Movie;
import com.example.app.movie.out.persistence.port.LoadMoviePort;
import com.example.app.movie.port.SearchMovieUseCase;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchMovieService implements SearchMovieUseCase {

    private final LoadMoviePort loadMoviePort;

    @Override
    public List<Movie> searchMovies(Predicate predicate) {
        return loadMoviePort.loadAllMovies(predicate);
    }
}
