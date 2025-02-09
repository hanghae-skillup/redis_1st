package com.hanghae.adapter.web;

import com.hanghae.application.dto.ApiResponse;
import com.hanghae.application.dto.request.MovieReservationRequestDto;
import com.hanghae.application.enums.ErrorCode;
import com.hanghae.application.enums.HttpStatusCode;
import com.hanghae.application.port.in.MovieReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

        if(response.success()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response); //조회 성공
        } else if(response.errorCode() == ErrorCode.RATE_LIMIT_EXCEEDED) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(response); // 조회 실패
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response); // 조회 실패
        }
    }
}
