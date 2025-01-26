package com.example.reservation;

import com.example.reservation.request.ReservationRequest;
import com.example.reservation.response.ReservationServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/reservation")
    public ReservationServiceResponse reserve(@RequestBody ReservationRequest request) {
        return reservationService.reserve(request.toServiceRequest());
    }
}
