package com.hanghae.application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MovieScheduleResponseDto(
        String title, //영화 제목
        String rating, //영상물 등급
        LocalDate releaseDate, //개봉일
        String thumbnailPath, // 썸네일 경로
        Long runningTimeMinutes, // 러닝타임(분)
        String genre, // 영화 장르
        String screenName, // 상영관 이름
        LocalDateTime showStartAt // 상영시작시간
) {}
