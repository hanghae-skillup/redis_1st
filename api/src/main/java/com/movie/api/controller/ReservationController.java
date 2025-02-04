package com.movie.api.controller;

import com.movie.api.response.ApiResponse;
import com.movie.application.service.ReservationService;
import com.movie.domain.entity.Reservation;
import com.movie.domain.entity.Seat;
import com.movie.infra.ratelimit.ReservationRateLimitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "예매", description = "예매 관련 API")
@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationRateLimitService reservationRateLimitService;

    @Operation(summary = "예매하기", description = "영화 좌석을 예매합니다.")
    @PostMapping
    public ResponseEntity<?> reserve(
            @Parameter(description = "사용자 ID") @RequestParam Long userId,
            @Parameter(description = "상영 일정 ID") @RequestParam Long scheduleId,
            @Parameter(description = "좌석 ID") @RequestParam Long seatId) {
        if (!reservationRateLimitService.canBook(String.valueOf(userId), String.valueOf(scheduleId))) {
            return ResponseEntity
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .body(ApiResponse.error(
                    String.valueOf(HttpStatus.TOO_MANY_REQUESTS.value()),
                    "예매 요청이 너무 빈번합니다. 잠시 후 다시 시도해주세요."
                ));
        }

        String reservationNumber = reservationService.reserve(userId, scheduleId, seatId);
        return ResponseEntity.ok(ApiResponse.success(reservationNumber));
    }

    @Operation(summary = "예매 조회", description = "예매 번호로 예매 정보를 조회합니다.")
    @GetMapping("/{reservationNumber}")
    public ResponseEntity<ApiResponse<Reservation>> getReservation(
            @Parameter(description = "예매 번호") @PathVariable String reservationNumber) {
        Reservation reservation = reservationService.getReservation(reservationNumber);
        return ResponseEntity.ok(ApiResponse.success(reservation));
    }

    @Operation(summary = "사용자별 예매 목록 조회", description = "사용자의 모든 예매 내역을 조회합니다.")
    @GetMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<List<Reservation>>> getUserReservations(
            @Parameter(description = "사용자 ID") @PathVariable Long userId) {
        List<Reservation> reservations = reservationService.getUserReservations(userId);
        return ResponseEntity.ok(ApiResponse.success(reservations));
    }

    @Operation(summary = "예매 취소", description = "예매를 취소합니다.")
    @DeleteMapping("/{reservationNumber}")
    public ResponseEntity<ApiResponse<Void>> cancelReservation(
            @Parameter(description = "예매 번호") @PathVariable String reservationNumber) {
        reservationService.cancelReservation(reservationNumber);
        return ResponseEntity.ok(ApiResponse.success((Void) null));
    }

    @Operation(summary = "예매 가능한 좌석 조회", description = "특정 상영 일정에 대해 예매 가능한 좌석 목록을 조회합니다.")
    @GetMapping("/schedules/{scheduleId}/seats")
    public ResponseEntity<ApiResponse<List<Seat>>> getAvailableSeats(
            @Parameter(description = "상영 일정 ID") @PathVariable Long scheduleId) {
        List<Seat> availableSeats = reservationService.getAvailableSeats(scheduleId);
        return ResponseEntity.ok(ApiResponse.success(availableSeats));
    }
} 