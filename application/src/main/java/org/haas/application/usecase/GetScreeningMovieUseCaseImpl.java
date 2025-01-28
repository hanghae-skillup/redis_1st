package org.haas.application.usecase;

import lombok.RequiredArgsConstructor;
import org.haas.core.domain.movie.Movie;
import org.haas.core.domain.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetScreeningMovieUseCaseImpl implements GetScreeningMovieUseCase {

    private final MovieRepository movieRepository;

    @Override
    public List<Movie> execute() {
        return movieRepository.findAllByMovieStatusOrderByReleasedAtDesc();
    }
}
