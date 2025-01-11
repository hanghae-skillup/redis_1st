package com.hanghae.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Movie extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId; // 영화 ID

    @Column(nullable = false, length = 100)
    private String title; // 영화 제목

    @Column(length = 50)
    private String rating; // 영상물 등급

    private LocalDate releaseDate; // 개봉일

    private String thumbnailUrl; // 썸네일 이미지(URL)

    private Integer duration; // 러닝 타임 (분)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre; // 장르 (외래키)

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Showtime> showtimes; // 상영 일정 (연관 관계)
}