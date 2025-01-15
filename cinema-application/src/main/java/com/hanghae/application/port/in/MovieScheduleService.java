package com.hanghae.application.port.in;

import com.hanghae.application.dto.MovieScheduleResponseDto;

import java.util.List;

public interface MovieScheduleService {
    List<MovieScheduleResponseDto> getMovieSchedules();
}
