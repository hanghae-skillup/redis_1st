package com.hanghae.infrastructure.entity;

import com.hanghae.domain.model.enums.MovieGenre;
import com.hanghae.domain.model.enums.MovieRating;
import com.hanghae.infrastructure.converter.MovieGenreConverter;
import com.hanghae.infrastructure.converter.MovieRatingConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;


@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name= "movie")
public class MovieEntity extends BaseEntity {
    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // 영화 제목

    @Convert(converter = MovieRatingConverter.class) //enum > db코드 변환
    @Column(nullable = false)
    private MovieRating rating; // 영상물 등급 ID

    @Column
    private LocalDate releaseDate; // 개봉일

    @Column(nullable = false)
    private Long runningTimeMinutes; // 러닝 타임(분)

    @Convert(converter = MovieGenreConverter.class) //enum > db코드 변환
    @Column(nullable = false)
    private MovieGenre genre; // 영화 장르 ID

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private UploadFileEntity uploadFileEntity; // 업로드 파일 (썸네일)

    @Builder
    public MovieEntity(Long id, String title, MovieRating rating, LocalDate releaseDate, Long runningTimeMinutes, MovieGenre genre, UploadFileEntity uploadFileEntity, Long createdBy, Long updatedBy) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.runningTimeMinutes = runningTimeMinutes;
        this.genre = genre;
        this.uploadFileEntity = uploadFileEntity;
        this.setCreatedBy(createdBy);
        this.setUpdatedBy(updatedBy);
    }
}
