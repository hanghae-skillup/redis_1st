package com.hanghae.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ShowingMovieScheduleResponseDto {
    private final Long movieId;
    private final String title;
    private final String rating;
    private final LocalDate releaseDate;
    private final String thumbnail;
    private final Long runningTimeMinutes;
    private final String genre;
    private final List<Schedule> schedules = new ArrayList<>();

    @Builder
    public ShowingMovieScheduleResponseDto(Long movieId, String title, String rating, LocalDate releaseDate, String thumbnail, Long runningTimeMinutes, String genre) {
        this.movieId = movieId;
        this.title = title; //영화 제목
        this.rating = rating; // 영상물 등급
        this.releaseDate = releaseDate; // 개봉일
        this.thumbnail = thumbnail; // 썸네일
        this.runningTimeMinutes = runningTimeMinutes; // 상영시간(분)
        this.genre = genre; // 영화 장르
    }

    public void addSchedule(Schedule schedule) {
        this.schedules.add(schedule);
    }

    @Getter
    @Builder
    public static class Schedule {
        private final String screenName; // 상영관 이름
        private final LocalDateTime showStartAt; // 상영 시작 시간
        private final LocalDateTime showEndAt; // 상영 종료 시간
    }
}
