package com.movie.domain.movie;

import com.movie.domain.movie.domain.Movie;

import java.util.List;

public interface MovieRepository {

    List<Movie> getMovies();

}
