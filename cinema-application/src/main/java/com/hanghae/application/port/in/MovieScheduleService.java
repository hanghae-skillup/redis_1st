package com.hanghae.application.port.in;

import com.hanghae.application.dto.ApiResponse;
import com.hanghae.application.dto.request.MovieScheduleRequestDto;
import com.hanghae.application.dto.response.MovieScheduleResponseDto;
import com.hanghae.application.dto.response.ShowingMovieScheduleResponseDto;

import java.util.List;

public interface MovieScheduleService {
    ApiResponse<List<MovieScheduleResponseDto>> getMovieSchedules();
    ApiResponse<List<ShowingMovieScheduleResponseDto>> getShowingMovieSchedules(MovieScheduleRequestDto requestDto, String ip);
    ApiResponse<Void> evictShowingMovieCache();
}
