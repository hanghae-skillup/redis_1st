package org.haas.application.usecase;

import org.haas.core.domain.movie.Movie;

@Deprecated
public interface CreateMovieUseCase {

    Movie createMovie(Movie movie);
}
