package com.dpflsy.common.dto;

import lombok.Data;

import java.util.List;
import java.time.LocalDateTime;

@Data
public class MovieResponse {
    private Long id;
    private String title;
    private String rating;
    private String genre;
    private String thumbnailUrl;
    private String releaseDate;
    private Integer runtime;
    private List<ScheduleResponse> schedules;

    @Data
    public static class ScheduleResponse {
        private Long id;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
    }
}