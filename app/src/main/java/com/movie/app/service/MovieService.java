package com.movie.app.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.movie.app.domain.Movie;
import com.movie.app.domain.MovieRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Cacheable(value = "Movies", key = "#title", cacheManager = "contentCacheManager")
    public List<Movie> getMoviesByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Cacheable(value = "Movies", key = "#genre", cacheManager = "contentCacheManager")
    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    @Cacheable(value = "Movies", key = "all", cacheManager = "contentCacheManager")
    public List<Movie> getMoviesByGenre() {
        return movieRepository.findAll();
    }

}
