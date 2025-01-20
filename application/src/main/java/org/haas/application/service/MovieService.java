package org.haas.application.service;

import java.util.List;

public interface MovieService {

    List<MovieDto> getAllMovieStatusIsShowing();

    MovieDto saveMovie(MovieDto movieDto);
}
