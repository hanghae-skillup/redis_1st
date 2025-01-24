package com.example.entity.movie;

import com.example.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Screening extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Long id;

    private LocalDate screeningAt;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @Builder
    public Screening(LocalDate screeningAt, LocalDateTime startedAt, LocalDateTime endedAt, Movie movie, Theater theater) {
        this.screeningAt = screeningAt;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.movie = movie;
        this.theater = theater;
    }
}