package com.movie.storage.movie.entity;

import com.movie.common.enums.FilmRating;
import com.movie.common.enums.Genre;
import com.movie.storage.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "movie")
public class MovieEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @Column(columnDefinition = "VARCHAR(100) NOT NULL COMMENT '제목'")
    private String title;                       // 영화 제목

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20) NOT NULL COMMENT '관람가'")
    private FilmRating filmRating;              // 영상물 등급

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20) NOT NULL COMMENT '장르'")
    private Genre genre;                        // 장르

    private LocalDateTime releasedAt;           // 개봉일

    @Column(columnDefinition = "VARCHAR(1000) NOT NULL COMMENT '썸네일 이미지 url'")
    private String thumbnailUrl;                // 썸네일 이미지 url

    @Column(columnDefinition = "VARCHAR(10) NOT NULL COMMENT '러닝 타임'")
    private String runningTime;                 // 러닝 타임

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieEntity movieEntity)) return false;
        return id != null && id.equals(movieEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
