package com.movie.moviedomain.movie;

import com.movie.moviedomain.movie.domain.Movie;

import java.util.List;

public interface MovieRepository {

    List<Movie> getMovies();

}
