package com.hanghae.adapter.web;

import com.hanghae.application.dto.ApiResponse;
import com.hanghae.application.dto.request.MovieReservationRequestDto;
import com.hanghae.application.enums.HttpStatusCode;
import com.hanghae.application.port.in.MovieReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<Void>> saveMovieReservation(@RequestBody MovieReservationRequestDto requestDto) {
        ApiResponse<Void> response = movieReservationService.saveMovieReservation(requestDto);

        //응답코드 일치시켜서 리턴
        return ResponseEntity.status(response.status().getCode()).body(response);
    }
}
