package com.hanghae.adapter.web;

import com.hanghae.application.dto.MovieScheduleResponseDto;
import com.hanghae.application.port.in.MovieScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {
    private final MovieScheduleService movieScheduleService;

    @GetMapping("/api/v1/movie-schedules")
    public List<MovieScheduleResponseDto> getMovieSchedules() {
        return movieScheduleService.getMovieSchedules();
    }
}
