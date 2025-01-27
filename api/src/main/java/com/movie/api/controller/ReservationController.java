package com.movie.api.controller;

import com.movie.application.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<String> reserve(
            @RequestParam Long userId,
            @RequestParam Long scheduleId,
            @RequestParam Long seatId) {
        String reservationNumber = reservationService.reserve(userId, scheduleId, seatId);
        return ResponseEntity.ok(reservationNumber);
    }
} 