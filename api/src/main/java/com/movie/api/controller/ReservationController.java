package com.movie.api.controller;

import com.movie.api.request.ReservationRequest;
import com.movie.api.response.ApiResponse;
import com.movie.domain.entity.Reservation;
import com.movie.domain.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "예매", description = "예매 관련 API")
@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @Operation(summary = "예매하기", description = "영화 좌석을 예매합니다.")
    @PostMapping
    public ApiResponse<Reservation> reserve(@RequestBody ReservationRequest request) {
        return ApiResponse.success(
                reservationService.reserve(request.userId(), request.scheduleId(), request.seatIds())
        );
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
    public ResponseEntity<ApiResponse<List<Reservation>>> getAvailableSeats(
            @Parameter(description = "상영 일정 ID") @PathVariable Long scheduleId) {
        List<Reservation> availableSeats = reservationService.getAvailableSeats(scheduleId);
        return ResponseEntity.ok(ApiResponse.success(availableSeats));
    }
} 