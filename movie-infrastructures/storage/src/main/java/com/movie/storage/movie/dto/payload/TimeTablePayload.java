package com.movie.storage.movie.dto.payload;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class TimeTablePayload {

    @Getter
    @NoArgsConstructor
    public static class Get {

        private LocalDateTime startTime;
        private LocalDateTime endTime;

        @QueryProjection
        public Get(LocalDateTime startTime, LocalDateTime endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

}
