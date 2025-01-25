package com.example.reservation.dto;

import lombok.Builder;

@Builder
public record ReservationResponse(Long reservationId) {
}
