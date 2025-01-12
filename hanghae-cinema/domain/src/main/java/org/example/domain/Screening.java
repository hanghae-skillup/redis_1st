package org.example.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class Screening {
    private Long id;
    private Movie movie;
    private String theater;
    private LocalDate showDate;
    private List<ScreeningSchedule> schedules;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}
