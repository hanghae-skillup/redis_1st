package com.bmsnc.adapter.out.persistence;

import com.bmsnc.applicaion.domain.model.MovieModel;
import com.bmsnc.applicaion.port.in.RunningMovieCommand;
import com.bmsnc.applicaion.port.out.RunningMoviesPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RunningMoviesAdapter implements RunningMoviesPort {

    private final ScheduleRepository scheduleRepository;
    private final MovieRepository movieRepository;
    private final MovieTheaterInfoRepository movieTheaterInfoRepository;

    @Override
    public List<MovieModel> getRunningMovies(RunningMovieCommand command) {

        return scheduleRepository.getRunningMovies(command.getTheaterId(), LocalDate.now())
                .stream()
                .map(Schedule::getMovieTheaterInfo)
                .filter(Objects::nonNull)
                .map(MovieTheaterInfo::getMovie)
                .filter(Objects::nonNull)
                .map(Movie::toModel)
                .collect(Collectors.toList());
    }
}
