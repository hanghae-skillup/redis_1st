package org.example.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ScreenInfoProjection {
    private String screenRoom;
    private String startTime;
    private String  endTime;

    public ScreenInfoProjection(String screenRoom, LocalDateTime startTime, LocalDateTime endTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.screenRoom = screenRoom;
        this.startTime = startTime.format(formatter);
        this.endTime = endTime.format(formatter);
    }
}
