package com.example.movie;

import com.example.movie.request.MovieSearchServiceRequest;
import com.example.movie.response.MovieResponse;
import com.example.movie.response.MoviesServiceResponse;
import com.example.repository.movie.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    @Transactional(readOnly = true)
    @Cacheable(value = "movies", key = "#request.title + ':' + #request.genre")
    public List<MovieResponse> getMovies(MovieSearchServiceRequest request) {
        return MoviesServiceResponse.of(movieRepository.findAllByTitleAndGenre(request.getTitle(), request.getGenre()));
    }
}
