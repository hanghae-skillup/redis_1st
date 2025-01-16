package com.example.movie;

import com.example.movie.request.MovieSearchServiceRequest;
import com.example.repository.MovieRepository;
import com.example.movie.response.MovieResponse;
import com.example.movie.response.MoviesServiceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<MovieResponse> getMovies(MovieSearchServiceRequest request) {
        return MoviesServiceResponse.of(movieRepository.findMovieWithScreeningAndTheater(Sort.by(Sort.Order.desc("releaseDate"))));
    }
}
