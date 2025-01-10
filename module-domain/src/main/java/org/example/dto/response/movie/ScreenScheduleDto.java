package org.example.dto.response.movie;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ScreenScheduleDto {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
