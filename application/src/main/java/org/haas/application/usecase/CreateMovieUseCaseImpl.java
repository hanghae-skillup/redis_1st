package org.haas.application.usecase;

import lombok.RequiredArgsConstructor;
import org.haas.core.domain.movie.Movie;
import org.haas.core.domain.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateMovieUseCaseImpl implements CreateMovieUseCase {

    private final MovieRepository movieRepository;
    @Override
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }
}
