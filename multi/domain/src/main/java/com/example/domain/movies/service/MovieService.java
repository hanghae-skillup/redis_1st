package com.example.domain.movies.service;

import com.example.domain.movies.entity.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Movie getMovieInfo(Long movieId){
        return movieRepository.getMovieInfo(movieId);
    }

}
