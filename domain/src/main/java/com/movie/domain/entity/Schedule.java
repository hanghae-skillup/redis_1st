package com.movie.domain.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "schedules")
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movie_id", nullable = false)
    private Long movieId;

    @Column(name = "theater_id", nullable = false)
    private Long theaterId;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Builder
    public Schedule(Long id, Long movieId, Long theaterId, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.movieId = movieId;
        this.theaterId = theaterId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void updateScheduleDateTime(LocalDateTime startAt, LocalDateTime endAt) {
        this.startTime = startAt;
        this.endTime = endAt;
    }

    public void updateTheater(Theater theater) {
        this.theaterId = theater.getId();
    }

    public void updateMovie(Movie movie) {
        this.movieId = movie.getId();
    }

    public Long getMovieId() {
        return movieId;
    }

    public Long getTheaterId() {
        return theaterId;
    }
}