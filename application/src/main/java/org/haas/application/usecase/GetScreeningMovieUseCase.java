package org.haas.application.usecase;

import org.haas.core.domain.movie.Movie;

import java.util.List;

public interface GetScreeningMovieUseCase {

    List<Movie> execute();

}
