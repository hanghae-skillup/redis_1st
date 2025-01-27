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

    private Long movieId;
    private Long theaterId;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    public Schedule(Long movieId, Long theaterId, LocalDateTime startAt, LocalDateTime endAt) {
        this.movieId = movieId;
        this.theaterId = theaterId;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public void updateScheduleDateTime(LocalDateTime startAt, LocalDateTime endAt) {
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public void updateTheaterId(Long theaterId) {
        this.theaterId = theaterId;
    }

    public void updateMovieId(Long movieId) {
        this.movieId = movieId;
    }
}