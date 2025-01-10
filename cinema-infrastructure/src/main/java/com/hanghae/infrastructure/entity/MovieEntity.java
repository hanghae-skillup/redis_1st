package com.hanghae.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name= "movie")
public class MovieEntity {
    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // 영화 제목

    @Column(nullable = false)
    private String ratingId; // 영상물 등급 ID

    @Column
    private LocalDate releaseDate; // 개봉일

    @Column(nullable = false)
    private Long runningTime; // 러닝 타임(분)

    @Column(nullable = false)
    private String genreId; // 영화 장르 ID

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private UploadFileEntity uploadFileEntity; // 업로드 파일 (썸네일)

    @Column
    private Long createdBy; // 작성자 ID

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt; // 작성일

    @Column
    private Long updatedBy; // 수정자 ID

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updatedAt; //수정일

    @Builder
    public MovieEntity(Long id, String title, String ratingId, LocalDate releaseDate, Long runningTime, String genreId, UploadFileEntity uploadFileEntity, Long createdBy, LocalDateTime createdAt, Long updatedBy, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.ratingId = ratingId;
        this.releaseDate = releaseDate;
        this.runningTime = runningTime;
        this.genreId = genreId;
        this.uploadFileEntity = uploadFileEntity;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }
}
