package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.baseresponse.BaseResponse;
import org.example.baseresponse.BaseResponseStatus;
import org.example.dto.request.ReservationRequestDto;
import org.example.service.reservation.ReservationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.example.baseresponse.BaseResponseStatus.SUCCESS;

@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/reservation")
    public BaseResponse<BaseResponseStatus> getPlayingMovies(@RequestBody @Validated ReservationRequestDto reservationRequestDto) {
        reservationService.reserveMovie(reservationRequestDto);
        return new BaseResponse<>(SUCCESS);
    }
}
