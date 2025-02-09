package com.hanghae.adapter.web;

import com.hanghae.application.dto.*;
import com.hanghae.application.dto.request.MovieScheduleRequestDto;
import com.hanghae.application.dto.response.MovieScheduleResponseDto;
import com.hanghae.application.dto.response.ShowingMovieScheduleResponseDto;
import com.hanghae.application.port.in.MovieScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MovieController {
    private final MovieScheduleService movieScheduleService;

    //영화 상영 시간표 조회
    @GetMapping("/v1/movie-schedules")
    public ResponseEntity<ApiResponse<List<MovieScheduleResponseDto>>> getMovieSchedules() {
        ApiResponse<List<MovieScheduleResponseDto>> response = movieScheduleService.getMovieSchedules();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //영화별 상영 시간표 조회 (grouping)
    @GetMapping("/v2/movie-schedules")
    public ResponseEntity<ApiResponse<List<ShowingMovieScheduleResponseDto>>> getShowingMovieSchedules(@ModelAttribute MovieScheduleRequestDto requestDto, HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        ApiResponse<List<ShowingMovieScheduleResponseDto>> response = movieScheduleService.getShowingMovieSchedules(requestDto, ip);

        if(response.success()) {
            return ResponseEntity.status(HttpStatus.OK).body(response); //조회 성공
        } else {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(response); // 조회 실패
        }
    }

    //redis 캐시삭제 (테스트용)
    @GetMapping("/test/evict-cache")
    public ResponseEntity<ApiResponse<Void>> evictCache() {
        ApiResponse<Void> response = movieScheduleService.evictShowingMovieCache();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
