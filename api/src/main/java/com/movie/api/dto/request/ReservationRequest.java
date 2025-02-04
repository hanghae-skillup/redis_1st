package com.movie.api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationRequest {
    private Long userId;
    private Long scheduleId;
    private Long seatId;
} 