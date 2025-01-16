package com.movie.storage.movie.repository;

import com.movie.domain.movie.MovieRepository;
import com.movie.domain.movie.domain.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MovieRepositoryImpl implements MovieRepository {

    private final MovieJpaRepository movieJpaRepository;

    @Override
    public List<Movie> getMovies() {
        return List.of();
    }
}
