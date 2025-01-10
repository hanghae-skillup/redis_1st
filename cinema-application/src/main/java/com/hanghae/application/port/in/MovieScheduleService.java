package com.hanghae.application.port.in;

import com.hanghae.application.dto.MovieScheduleDto;

import java.util.List;

public interface MovieScheduleService {
    List<MovieScheduleDto> getMovieSchedules();
}
