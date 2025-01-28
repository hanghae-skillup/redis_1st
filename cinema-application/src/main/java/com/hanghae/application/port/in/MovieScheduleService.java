package com.hanghae.application.port.in;

import com.hanghae.application.dto.ApiResponse;
import com.hanghae.application.dto.MovieScheduleRequestDto;
import com.hanghae.application.dto.MovieScheduleResponseDto;
import com.hanghae.application.dto.ShowingMovieScheduleResponseDto;

import java.util.List;

public interface MovieScheduleService {
    ApiResponse<List<MovieScheduleResponseDto>> getMovieSchedules();
    ApiResponse<List<ShowingMovieScheduleResponseDto>> getShowingMovieSchedules(MovieScheduleRequestDto requestDto);
    ApiResponse<Void> evictShowingMovieCache();
}
