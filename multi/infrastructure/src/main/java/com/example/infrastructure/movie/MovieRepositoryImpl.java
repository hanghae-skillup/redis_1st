package com.example.infrastructure.movie;

import com.example.domain.movies.entity.Movie;
import com.example.domain.movies.service.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MovieRepositoryImpl implements MovieRepository {

    private final MovieJpaRepository movieJpaRepository;

    @Override
    public Movie getMovieInfo(Long movieId) {
        return movieJpaRepository.findById(movieId).orElse(null);
    }
}
