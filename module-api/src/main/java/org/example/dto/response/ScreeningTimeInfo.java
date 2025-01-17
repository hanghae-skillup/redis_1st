package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScreeningTimeInfo {
    private LocalDateTime startTime;
    private LocalDateTime  endTime;
}
