package com.hanghae.adapter.web;

import com.hanghae.application.dto.*;
import com.hanghae.application.port.in.MovieScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public ApiResponse<List<ShowingMovieScheduleResponseDto>> getShowingMovieSchedules(@ModelAttribute MovieScheduleRequestDto requestDto, HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return movieScheduleService.getShowingMovieSchedules(requestDto, ip);
    }

    //redis 캐시삭제 (테스트용)
    @GetMapping("/test/evict-cache")
    public ApiResponse<Void> evictCache() {
        return movieScheduleService.evictShowingMovieCache();
    }
}
