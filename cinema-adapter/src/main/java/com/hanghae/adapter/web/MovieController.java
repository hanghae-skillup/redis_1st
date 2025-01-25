package com.hanghae.adapter.web;

import com.hanghae.application.dto.ApiResponse;
import com.hanghae.application.dto.MovieScheduleRequestDto;
import com.hanghae.application.dto.MovieScheduleResponseDto;
import com.hanghae.application.dto.ShowingMovieScheduleResponseDto;
import com.hanghae.application.port.in.MovieScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MovieController {
    private final MovieScheduleService movieScheduleService;

    //영화 상영 시간표 조회
    @GetMapping("/v1/movie-schedules")
    public ApiResponse<List<MovieScheduleResponseDto>> getMovieSchedules() {
        return movieScheduleService.getMovieSchedules();
    }

    //영화별 상영 시간표 조회 (grouping)
    @GetMapping("/v2/movie-schedules")
    public ApiResponse<List<ShowingMovieScheduleResponseDto>> getShowingMovieSchedules(@ModelAttribute MovieScheduleRequestDto requestDto) {
        return movieScheduleService.getShowingMovieSchedules(requestDto);
    }

    //redis 캐시삭제 (테스트용)
    @GetMapping("/test/evict-cache")
    public ApiResponse<Void> evictCache() {
        return movieScheduleService.evictShowingMovieCache();
    }
}
