package com.movie.moviedomain.movie.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeTable {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public TimeTable(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static TimeTable of(LocalDateTime startTime, LocalDateTime endTime) {
        return new TimeTable(startTime, endTime);
    }

}
