package com.example.application.dto.request;

import java.util.List;

public record ReservationRequestDto(
        Long screeningId,
        Long memberId,
        List<Long> seatIds
) {}
