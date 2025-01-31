package com.hanghae.adapter.web;

import com.hanghae.application.dto.ApiResponse;
import com.hanghae.application.dto.MovieReservationRequestDto;
import com.hanghae.application.port.in.MovieReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReservationController {
    private final MovieReservationService movieReservationService;

    //영화 예약
    // TODO :: 좌석선택API와 영화예약API 분리
    @PostMapping("/v1/movie-reservation")
    public ApiResponse<Void> saveMovieReservation(@RequestBody MovieReservationRequestDto requestDto) {
        return movieReservationService.saveMovieReservation(requestDto);
    }
}
