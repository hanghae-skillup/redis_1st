package com.example.app.movie.service;

import com.example.app.movie.Movie;
import com.example.app.movie.out.persistence.adapter.MoviePersistenceAdapter;
import com.example.app.movie.port.in.MovieUseCase;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService implements MovieUseCase {

    private final MoviePersistenceAdapter moviePersistenceAdapter;

    @Override
    public List<Movie> searchMovies(Predicate predicate) {
        return moviePersistenceAdapter.findAllBy(predicate);
    }
}
