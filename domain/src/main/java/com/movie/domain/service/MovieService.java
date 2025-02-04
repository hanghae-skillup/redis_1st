package com.movie.domain.service;

import com.movie.domain.entity.Movie;
import com.movie.domain.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public List<Movie> getCurrentMovies() {
        return movieRepository.findCurrentMovies(LocalDateTime.now());
    }

    @Transactional(readOnly = true)
    public List<Movie> getUpcomingMovies() {
        return movieRepository.findUpcomingMovies(LocalDateTime.now());
    }
} 