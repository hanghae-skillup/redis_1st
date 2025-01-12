package com.bmsnc.adapter.out.persistence;

import com.bmsnc.applicaion.domain.model.MovieModel;
import com.bmsnc.applicaion.port.in.RunningMovieCommand;
import com.bmsnc.applicaion.port.out.RunningMoviesPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RunningMoviesAdapter implements RunningMoviesPort {

    private final MovieRepository movieRepository;
    private final MovieTheaterInfoRepository movieTheaterInfoRepository;

    @Override
    public List<MovieModel> getRunningMovies(RunningMovieCommand command) {
        return movieRepository.getRunningMovies(command.getTheaterId(), command.getNow())
                        .stream().map(movieTheaterInfo -> movieTheaterInfo.getMovie().toModel())
                .collect(Collectors.toList());

    }
}
