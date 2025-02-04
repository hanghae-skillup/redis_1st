package com.example.app.movie.port;

import com.example.app.movie.domain.Movie;
import com.example.app.movie.dto.SearchMovieCommand;

import java.util.List;

public interface LoadMoviePort {
    List<Movie> loadAllMovies(SearchMovieCommand searchMovieCommand);
}
