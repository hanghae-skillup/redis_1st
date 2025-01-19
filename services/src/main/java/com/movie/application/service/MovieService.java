package com.movie.application.service;

import com.movie.application.dto.MovieResponseDto;
import com.movie.domain.dto.MovieProjection;
import com.movie.domain.dto.MovieSearchCondition;
import com.movie.domain.entity.Movie;
import com.movie.domain.entity.Schedule;
import com.movie.domain.repository.MovieRepository;
import com.movie.domain.repository.ScheduleRepository;
import com.movie.infra.repository.MovieQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {
    private final MovieRepository movieRepository;
    private final ScheduleRepository scheduleRepository;
    private final MovieQueryRepository movieQueryRepository;

    public List<MovieResponseDto> getNowShowingMovies(MovieSearchCondition condition) {
        List<MovieProjection> movieList = condition != null ? 
            movieQueryRepository.search(condition) :
            movieRepository.findAll().stream()
                .filter(movie -> movie.getReleaseDate() != null)
                .sorted(Comparator.comparing(Movie::getReleaseDate).reversed())
                .map(movie -> new MovieProjection() {
                    @Override
                    public Long getId() {
                        return movie.getId();
                    }
                    @Override
                    public String getTitle() {
                        return movie.getTitle();
                    }
                    @Override
                    public String getThumbnail() {
                        return movie.getThumbnailUrl();
                    }
                    @Override
                    public Integer getRunningTime() {
                        return movie.getRunningTime();
                    }
                    @Override
                    public String getGenre() {
                        return movie.getGenre();
                    }
                })
                .collect(Collectors.toList());

        List<Schedule> scheduleList = scheduleRepository.findAllFetchMovieTheater().stream()
                .filter(schedule -> schedule.getStartAt() != null)
                .sorted(Comparator.comparing(Schedule::getStartAt))
                .toList();

        return movieList.stream()
                .map(movie -> MovieResponseDto.builder()
                        .id(movie.getId())
                        .title(movie.getTitle())
                        .thumbnail(movie.getThumbnail())
                        .runningTime(movie.getRunningTime())
                        .genre(movie.getGenre())
                        .schedules(
                                scheduleList.stream()
                                        .filter(schedule -> schedule.getMovie().getId().equals(movie.getId()))
                                        .map(schedule -> MovieResponseDto.ScheduleInfo.builder()
                                                .id(schedule.getId())
                                                .startAt(schedule.getStartAt())
                                                .theater(MovieResponseDto.TheaterInfo.builder()
                                                        .id(schedule.getTheater().getId())
                                                        .name(schedule.getTheater().getName())
                                                        .build())
                                                .build())
                                        .collect(Collectors.toList())
                        )
                        .build())
                .collect(Collectors.toList());
    }
}
