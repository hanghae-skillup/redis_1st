package com.example.reservation.presentation.controller;

import com.example.reservation.application.dto.ReserveResponse;
import com.example.reservation.application.service.ReservationService;
import com.example.reservation.presentation.dto.ReserveRequest;
import com.example.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/v1/reservation")
    public BaseResponse<ReserveResponse> reserveSeat(@Validated @RequestBody ReserveRequest reserveRequestDo) {
        ReserveResponse response = reservationService.reserveSeat(reserveRequestDo);
        return new BaseResponse<>(response);
    }
}
