package com.movie.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class MovieResponseDto {
    private Long id;
    private String title;
    private String thumbnail;
    private Integer runningTime;
    private String genre;
    private List<ScheduleInfo> schedules;

    @Getter
    @Builder
    public static class ScheduleInfo {
        private Long id;
        private LocalDateTime startAt;
        private TheaterInfo theater;
    }

    @Getter
    @Builder
    public static class TheaterInfo {
        private Long id;
        private String name;
    }
}
