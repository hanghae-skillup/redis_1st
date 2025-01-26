package com.example.api.screening.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@Getter
@AllArgsConstructor
@Builder
public class ScreeningResponseDto {
    private String title;
    private String thumbnailUrl;
    private String rating;
    private LocalDateTime releaseDate;
    private int runtimeMinutes;
    private String genre;
    private List<TheaterSchedule> theaterSchedules; // 상영관별 시간표 리스트

    @Getter
    @AllArgsConstructor
    @Builder
    public static class TheaterSchedule {
        private String theaterName; // 상영관 이
        private List<Schedule> schedules; // 해당 상영관의 상영 시간표

        @Getter
        @AllArgsConstructor
        public static class Schedule {
            private LocalTime startTime; // 상영 시작 시간
            private LocalTime endTime;   // 상영 종료 시간
        }
    }
}