package com.example.app.movie.port.in;

import com.example.app.movie.domain.Movie;
import com.example.app.movie.dto.request.MovieSearchRequest;

import java.util.List;

public interface SearchMovieUseCase {
    List<Movie> searchMovies(MovieSearchRequest movieSearchRequest);
}
