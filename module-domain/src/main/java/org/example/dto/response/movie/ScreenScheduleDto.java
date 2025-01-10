package org.example.dto.response.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ScreenScheduleDto {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
