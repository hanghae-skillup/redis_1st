package com.bmsnc.adapter.out.persistence;

import com.bmsnc.adapter.out.querydsl.model.MovieQueryModel;
import com.bmsnc.applicaion.domain.model.MovieModel;
import com.bmsnc.applicaion.port.in.RunningMovieCommand;
import com.bmsnc.applicaion.port.out.RunningMoviesPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RunningMoviesAdapter implements RunningMoviesPort {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleQueryRepository scheduleQueryRepository;

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

    @Override
    public List<MovieModel> searchRunningMovies(RunningMovieCommand command) {

        return scheduleQueryRepository.searchRunningMovies(command)
                .stream()
                .map(MovieQueryModel::toModel)
                .collect(Collectors.toList());
    }
}
