package com.hanghae.domain.model;

import com.hanghae.domain.model.enums.MovieGenre;
import com.hanghae.domain.model.enums.MovieRating;
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
    private final MovieRating rating; // 영상물 등급
    private final LocalDate releaseDate; // 개봉일
    private final Long runningTimeMinutes; // 러닝 타임(분)
    private final MovieGenre genre; // 영화 장르
    private final UploadFile uploadFile; // 썸네일 ID
    private final Long createdBy; // 작성자 ID
    private final LocalDateTime createdAt; // 작성일
    private final Long updatedBy; // 수정자 ID
    private final LocalDateTime updatedAt; //수정일
}
