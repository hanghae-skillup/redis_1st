package com.hanghae.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Movie {
    //일관된 상태 유지를 위해 @AllArgsConstructor 만 사용
    private final Long movieId;
    private final String title; // 영화 제목
    private final String ratingId; // 영상물 등급 ID
    private final LocalDate releaseDate; // 개봉일
    private final Long runningTime; // 러닝 타임(분)
    private final String genreId; // 영화 장르 ID
    private final UploadFile uploadFile; // 썸네일 ID
    private final Long createdBy; // 작성자 ID
    private final LocalDateTime createdAt; // 작성일
    private final Long updatedBy; // 수정자 ID
    private final LocalDateTime updatedAt; //수정일
}
