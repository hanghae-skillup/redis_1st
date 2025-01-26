package com.example.movie.service;

import com.example.jpa.entity.movie.Genre;
import com.example.jpa.repository.movie.MovieRepository;
import com.example.jpa.repository.movie.dto.MoviesDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieCacheService {

    private final MovieRepository movieRepository;

    @Cacheable(
            cacheNames = "getMoviesByGenre",
            key = "'movies:genre:' +  #p0",
            cacheManager = "cacheManager"
    )
    public List<MoviesDetailDto> getMoviesByGenre(String genre) {
        return movieRepository.searchWithFiltering(null, Genre.valueOf(genre), null);
    }

}
