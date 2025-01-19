package com.movie.domain.entity;

import com.querydsl.core.annotations.QueryEntity;
import jakarta.persistence.Table;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Getter
@QueryEntity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "movie")
public class Movie extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String grade;
    private String genre;
    private Integer runningTime;
    private LocalDate releaseDate;
    private String thumbnailUrl;

    public Movie(String title, String grade, String genre, Integer runningTime, LocalDate releaseDate, String thumbnailUrl) {
        this.title = title;
        this.grade = grade;
        this.genre = genre;
        this.runningTime = runningTime;
        this.releaseDate = releaseDate;
        this.thumbnailUrl = thumbnailUrl;
    }

    // 영화 정보 수정을 위한 비즈니스 메서드
    public void updateMovieInfo(String title, String grade, String genre, Integer runningTime, LocalDate releaseDate, String thumbnailUrl) {
        this.title = title;
        this.grade = grade;
        this.genre = genre;
        this.runningTime = runningTime;
        this.releaseDate = releaseDate;
        this.thumbnailUrl = thumbnailUrl;
    }

    // 썸네일 URL만 수정하는 비즈니스 메서드
    public void updateThumbnail(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}