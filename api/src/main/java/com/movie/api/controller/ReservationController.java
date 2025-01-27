package com.movie.api.controller;

import com.movie.application.service.ReservationService;
import com.movie.domain.entity.Reservation;
import com.movie.domain.entity.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{reservationNumber}")
    public ResponseEntity<Reservation> getReservation(@PathVariable String reservationNumber) {
        Reservation reservation = reservationService.getReservation(reservationNumber);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Reservation>> getUserReservations(@PathVariable Long userId) {
        List<Reservation> reservations = reservationService.getUserReservations(userId);
        return ResponseEntity.ok(reservations);
    }

    @DeleteMapping("/{reservationNumber}")
    public ResponseEntity<Void> cancelReservation(@PathVariable String reservationNumber) {
        reservationService.cancelReservation(reservationNumber);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/schedules/{scheduleId}/seats")
    public ResponseEntity<List<Seat>> getAvailableSeats(@PathVariable Long scheduleId) {
        List<Seat> availableSeats = reservationService.getAvailableSeats(scheduleId);
        return ResponseEntity.ok(availableSeats);
    }
} 