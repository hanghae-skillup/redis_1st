package com.movie.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MovieResponseDto implements Serializable {
    private Long id;
    private String title;
    private String thumbnail;
    private Integer runningTime;
    private String genre;
    
    @Builder.Default
    private List<ScheduleInfo> schedules = new ArrayList<>();

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static class ScheduleInfo implements Serializable {
        private Long id;
        private LocalDateTime startAt;
        private TheaterInfo theater;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static class TheaterInfo implements Serializable {
        private Long id;
        private String name;
    }
}
