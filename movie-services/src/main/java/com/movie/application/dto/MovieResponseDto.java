package com.movie.application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MovieResponseDto {

    private String title;
    private String grade;
    private LocalDate releaseDate;
    private String thumbnailUrl;
    private int runningTime;
    private String genre;
    private List<ScheduleInfo> schedules;

    @Getter
    @Setter
    public static class ScheduleInfo {
        private String theaterName;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
    }
}
