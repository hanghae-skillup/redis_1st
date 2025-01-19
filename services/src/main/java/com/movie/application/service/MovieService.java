package com.movie.application.service;

import com.movie.application.dto.MovieResponseDto;
import com.movie.domain.dto.MovieProjection;
import com.movie.domain.dto.MovieSearchCondition;
import com.movie.domain.entity.Schedule;
import com.movie.domain.repository.MovieRepository;
import com.movie.domain.repository.ScheduleRepository;
import com.movie.infra.repository.MovieQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final ScheduleRepository scheduleRepository;
    private final MovieQueryRepository movieQueryRepository;

    @Cacheable(value = "movies", key = "#condition.title + '_' + #condition.genre")
    public List<MovieResponseDto> getNowShowingMovies(MovieSearchCondition condition) {
        List<MovieProjection> movies = movieQueryRepository.search(condition);
        List<Schedule> schedules = scheduleRepository.findAllFetchMovieTheater();

        Map<Long, List<Schedule>> schedulesByMovieId = schedules.stream()
                .filter(schedule -> schedule.getStartAt().isAfter(LocalDateTime.now()))
                .collect(Collectors.groupingBy(schedule -> schedule.getMovie().getId()));

        return movies.stream()
                .map(movie -> MovieResponseDto.builder()
                        .id(movie.getId())
                        .title(movie.getTitle())
                        .thumbnail(movie.getThumbnail())
                        .runningTime(movie.getRunningTime())
                        .genre(movie.getGenre())
                        .schedules(schedulesByMovieId.getOrDefault(movie.getId(), List.of()).stream()
                                .map(schedule -> MovieResponseDto.ScheduleInfo.builder()
                                        .id(schedule.getId())
                                        .startAt(schedule.getStartAt())
                                        .theater(MovieResponseDto.TheaterInfo.builder()
                                                .id(schedule.getTheater().getId())
                                                .name(schedule.getTheater().getName())
                                                .build())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }
}
