package com.movie.domain.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theater theater;

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    public Schedule(Movie movie, Theater theater, LocalDateTime startAt, LocalDateTime endAt) {
        this.movie = movie;
        this.theater = theater;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public void updateScheduleDateTime(LocalDateTime startAt, LocalDateTime endAt) {
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public void updateTheater(Theater theater) {
        this.theater = theater;
    }

    public void updateMovie(Movie movie) {
        this.movie = movie;
    }

    public Long getMovieId() {
        return movie != null ? movie.getId() : null;
    }

    public Long getTheaterId() {
        return theater != null ? theater.getId() : null;
    }
}