package org.haas.application.service;

import lombok.RequiredArgsConstructor;
import org.haas.application.usecase.GetScreeningMovieUseCase;
import org.haas.core.domain.movie.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final GetScreeningMovieUseCase getScreeningMovieUseCase;

    @Override
    public List<Movie> getAllMovieStatusIsShowing() {
        return getScreeningMovieUseCase.execute();
    }

}
