package com.hanghae.adapter.web;

import com.hanghae.application.dto.MovieScheduleResponseDto;
import com.hanghae.application.dto.ShowingMovieScheduleResponseDto;
import com.hanghae.application.port.in.MovieScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {
    private final MovieScheduleService movieScheduleService;

    //영화 상영 시간표 조회
    @GetMapping("/api/v1/movie-schedules")
    public List<MovieScheduleResponseDto> getMovieSchedules() {
        return movieScheduleService.getMovieSchedules();
    }

    //영화별 상영 시간표 조회 (grouping)
    @GetMapping("/api/v2/movie-schedules")
    public List<ShowingMovieScheduleResponseDto> getShowingMovieSchedules() {
        return movieScheduleService.getShowingMovieSchedules();
    }
}
