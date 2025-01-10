package com.hanghae.adapter.web;

import com.hanghae.application.dto.MovieScheduleDto;
import com.hanghae.application.port.in.MovieScheduleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {
    private final MovieScheduleService movieScheduleService;

    public MovieController(MovieScheduleService movieScheduleService) {
        this.movieScheduleService = movieScheduleService;
    }

    @GetMapping("/movies/schedules")
    public List<MovieScheduleDto> getMovieSchedules() {
        return movieScheduleService.getMovieSchedules();
    }
}
