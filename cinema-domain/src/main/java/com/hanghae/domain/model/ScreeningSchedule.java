package com.hanghae.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ScreeningSchedule {
    //일관된 상태 유지를 위해 @AllArgsConstructor 만 사용
    private final Long scheduleId;
    private final Movie movie; // 영화
    private final Screen screen; // 상영관
    private final LocalDateTime showStartDatetime;
    private final Long createdBy; // 작성자 ID
    private final LocalDateTime createdAt; // 작성일
    private final Long updatedBy; // 수정자 ID
    private final LocalDateTime updatedAt; //수정일

    public ScreeningSchedule(Long scheduleId, Movie movie, Screen screen, LocalDateTime showStartDatetime, Long createdBy, LocalDateTime createdAt, Long updatedBy, LocalDateTime updatedAt) {
        this.scheduleId = scheduleId;
        this.movie = movie;
        this.screen = screen;
        this.showStartDatetime = showStartDatetime;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;

        // 개봉일 검증 로직
        validateReleaseDate(movie.getReleaseDate());
    }

    public void validateReleaseDate(LocalDate releaseDate) {
        if (showStartDatetime.toLocalDate().isBefore(releaseDate)) {
            throw new IllegalArgumentException("상영 날짜는 개봉일보다 이전일 수 없습니다.");
        }
    }
}
