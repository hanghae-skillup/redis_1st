package com.hanghae.common.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class MovieResponseDto {

    private String title;          // 영화 제목
    private String rating;         // 영상물 등급
    private LocalDate releaseDate; // 개봉일
    private String thumbnailUrl;   // 썸네일 이미지 URL
    private Integer duration;      // 러닝 타임 (분)
    private String genre;          // 영화 장르
    private List<ShowtimeDto> showtimes; // 상영 시간표

    @Data
    @AllArgsConstructor
    public static class ShowtimeDto {
        private String theaterName;      // 상영관 이름
        private LocalDateTime startTime; // 상영 시작 시간
        private LocalDateTime endTime;   // 상영 종료 시간
    }
}