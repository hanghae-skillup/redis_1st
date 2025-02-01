package com.example.reservation;

import com.example.reservation.request.ReservationRequest;
import com.example.reservation.response.ReservationServiceResponse;
import com.example.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/v1/reservation")
    public ApiResponse<ReservationServiceResponse> reserve(@RequestBody ReservationRequest request) {
        return ApiResponse.created("영화예매 성공", reservationService.reserve(request.toServiceRequest()));
    }
}
