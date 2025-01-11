package com.example;

import com.example.repository.MovieRepository;
import com.example.response.MovieResponse;
import com.example.response.MoviesServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<MovieResponse> getMovies() {
        return MoviesServiceResponse.of(movieRepository.findMovieWithScreeningAndTheater());
    }
}
