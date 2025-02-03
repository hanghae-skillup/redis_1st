package com.example.reservation.controller;

import com.example.reservation.dto.ReservationRequest;
import com.example.reservation.service.ReservationService;
import com.example.response.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/v1/reservation")
    public BaseResponse getMoviesNowShowing(@RequestBody @Valid ReservationRequest reservationRequest) throws InterruptedException {
        reservationService.reserveSeat(reservationRequest);
        return new BaseResponse<>();
    }
}
