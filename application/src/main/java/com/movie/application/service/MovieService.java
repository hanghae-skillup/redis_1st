package com.movie.application.service;

import com.movie.application.dto.MovieResponseDto;
import com.movie.domain.dto.MovieSearchCondition;
import com.movie.domain.entity.Movie;
import com.movie.infra.repository.MovieJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {
    
    private final MovieJpaRepository movieRepository;
    
    public List<MovieResponseDto> getNowShowingMovies(MovieSearchCondition condition) {
        List<Movie> movies = movieRepository.findNowShowingMovies(condition);
        return movies.stream()
                .map(MovieResponseDto::from)
                .collect(Collectors.toList());
    }
} 