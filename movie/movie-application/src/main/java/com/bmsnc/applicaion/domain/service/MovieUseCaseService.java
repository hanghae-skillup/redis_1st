package com.bmsnc.applicaion.domain.service;


import com.bmsnc.applicaion.port.in.MovieUseCase;
import com.bmsnc.applicaion.port.in.RunningMovieCommand;
import com.bmsnc.applicaion.port.out.RunningMoviesPort;
import com.bmsnc.common.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MovieUseCaseService implements MovieUseCase {

    private final RunningMoviesPort runningMoviesPort;

    @Override
    public Result getRunningMovies(RunningMovieCommand command) {

        return Result.builder()
                .status(HttpStatus.OK.value())
                .data(runningMoviesPort.getRunningMovies(command))
                .build();
    }
    @Override
    public Result searchRunningMovies(@Valid RunningMovieCommand command) {
        return Result.builder()
                .status(HttpStatus.OK.value())
                .data(runningMoviesPort.searchRunningMovies(command))
                .build();
    }
}
