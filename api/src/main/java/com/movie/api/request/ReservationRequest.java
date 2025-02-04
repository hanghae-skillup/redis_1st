package com.movie.api.request;

import java.util.List;

public record ReservationRequest(
        Long userId,
        Long scheduleId,
        List<Long> seatIds
) {} 