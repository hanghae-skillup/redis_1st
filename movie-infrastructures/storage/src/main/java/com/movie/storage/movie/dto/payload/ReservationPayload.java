package com.movie.storage.movie.dto.payload;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReservationPayload {

    @Getter
    @NoArgsConstructor
    public static class Get {
        private Long scheduleId;
        private Long seatId;
        private Long userId;
        private LocalDateTime reservedAt;

        @QueryProjection
        public Get(Long scheduleId, Long seatId, Long userId, LocalDateTime reservedAt) {
            this.scheduleId = scheduleId;
            this.seatId = seatId;
            this.userId = userId;
            this.reservedAt = reservedAt;
        }
    }

}
