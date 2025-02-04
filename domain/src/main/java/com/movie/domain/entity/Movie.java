package com.movie.domain.entity;

import com.querydsl.core.annotations.QueryEntity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Entity
@Getter
@QueryEntity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "movie")
public class Movie extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String grade;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(name = "running_time", nullable = false)
    private Integer runningTime;

    @Column(nullable = false)
    private LocalDateTime releaseDate;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Builder
    private Movie(String title, String grade, String genre, String description, Integer runningTime, LocalDateTime releaseDate, String thumbnailUrl) {
        this.title = title;
        this.grade = grade;
        this.genre = genre;
        this.description = description;
        this.runningTime = runningTime;
        this.releaseDate = releaseDate;
        this.thumbnailUrl = thumbnailUrl;
    }

    // 영화 정보 수정을 위한 비즈니스 메서드
    public void updateMovieInfo(String title, String grade, String genre, String description, Integer runningTime, LocalDateTime releaseDate, String thumbnailUrl) {
        this.title = title;
        this.grade = grade;
        this.genre = genre;
        this.description = description;
        this.runningTime = runningTime;
        this.releaseDate = releaseDate;
        this.thumbnailUrl = thumbnailUrl;
    }

    // 썸네일 URL만 수정하는 비즈니스 메서드
    public void updateThumbnail(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}