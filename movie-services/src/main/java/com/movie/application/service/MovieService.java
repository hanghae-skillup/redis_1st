package com.movie.application.service;

import com.movie.application.dto.MovieResponseDto;
import com.movie.application.dto.MovieResponseDto.ScheduleInfo;
import com.movie.domain.dto.MovieSearchCondition;
import com.movie.domain.entity.Movie;
import com.movie.domain.entity.Schedule;
import com.movie.domain.repository.MovieRepository;
import com.movie.domain.repository.ScheduleRepository;
import com.movie.infra.repository.MovieQueryRepository;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final ScheduleRepository scheduleRepository;
    private final MovieQueryRepository movieQueryRepository;

    public List<MovieResponseDto> getNowShowingMovies(MovieSearchCondition condition) {
        List<Movie> movieList = condition != null ? 
            movieQueryRepository.search(condition) :
            movieRepository.findAll().stream()
                .filter(movie -> movie.getReleaseDate() != null)
                .sorted(Comparator.comparing(Movie::getReleaseDate).reversed())
                .toList();

        List<Schedule> scheduleList = scheduleRepository.findAllFetchMovieTheater().stream()
            .filter(schedule -> schedule.getStartAt() != null)
            .sorted(Comparator.comparing(Schedule::getStartAt))
            .toList();

        return movieList.stream()
            .map(movie -> {
                List<ScheduleInfo> scheduleInfos = scheduleList.stream()
                    .filter(sch -> sch.getMovie().getId().equals(movie.getId()))
                    .map(sch -> {
                        ScheduleInfo info = new ScheduleInfo();
                        info.setTheaterName(sch.getTheater().getName());
                        info.setStartTime(sch.getStartAt());
                        info.setEndTime(sch.getEndAt());
                        return info;
                    })
                    .toList();

                MovieResponseDto dto = new MovieResponseDto();
                dto.setTitle(movie.getTitle());
                dto.setGrade(movie.getGrade());
                dto.setReleaseDate(movie.getReleaseDate());
                dto.setThumbnailUrl(movie.getThumbnailUrl());
                dto.setRunningTime(movie.getRunningTime() != null ? movie.getRunningTime() : 0);
                dto.setGenre(movie.getGenre());
                dto.setSchedules(scheduleInfos);
                return dto;
            })
            .toList();
    }
}
