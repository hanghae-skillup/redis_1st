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
            key = "'movies:genre:' + #genre",
            cacheManager = "cacheManager"
    )
    public List<MoviesDetailDto> getMoviesByGenre(Genre genre) {
        System.out.println("test: "+genre);
        return movieRepository.searchWithFiltering(null, genre, null);
    }

}
