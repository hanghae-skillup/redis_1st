package org.example.port.inbound.dto;


import org.example.domain.ScreeningSchedule;

import java.time.LocalDateTime;

public record ShowTimeDto(
        LocalDateTime startTime,
        LocalDateTime endTime
) {
    public static ShowTimeDto of(ScreeningSchedule schedule) {
        return new ShowTimeDto(schedule.getStartTime(), schedule.getEndTime());
    }
}
