package com.movie.domain.movie.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    private Long scheduleId;
    private Long seatId;
    private Long userId;
    private LocalDateTime reservedAt;

}
