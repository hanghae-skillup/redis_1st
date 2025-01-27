package com.example.infrastructure.web;

import com.example.application.dto.request.ReservationRequestDto;
import com.example.application.dto.response.ReservationResponseDto;
import com.example.application.port.in.ReservationServicePort;
import com.example.domain.validation.ReservationValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    private final ReservationServicePort reservationServicePort;
    private final ReservationValidation reservationValidation;

    @PostMapping
    public ResponseEntity<ReservationResponseDto> createReservation(@RequestBody ReservationRequestDto request) {
        reservationValidation.validateReservationRequest(request.screeningId(), request.memberId(), request.seatIds());

        ReservationResponseDto response = reservationServicePort.create(request);
        return ResponseEntity.ok(response);
    }

}
