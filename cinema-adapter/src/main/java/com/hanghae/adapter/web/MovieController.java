package com.hanghae.adapter.web;

import com.hanghae.application.dto.*;
import com.hanghae.application.port.in.MovieReservationService;
import com.hanghae.application.port.in.MovieScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MovieController {
    private final MovieScheduleService movieScheduleService;
    private final MovieReservationService movieReservationService;

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

    //영화 예약
    // TODO :: 좌석선택API와 영화예약API 분리
    @PostMapping("/v1/movie-reservation")
    public ApiResponse<Void> saveMovieReservation(@RequestBody MovieReservationRequestDto requestDto) {
        return movieReservationService.saveMovieReservation(requestDto);
    }

    //redis 캐시삭제 (테스트용)
    @GetMapping("/test/evict-cache")
    public ApiResponse<Void> evictCache() {
        return movieScheduleService.evictShowingMovieCache();
    }
}
