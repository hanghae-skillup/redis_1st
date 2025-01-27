package com.movie.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reservations")
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @Column(nullable = false)
    private String reservationNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;

    @Column(nullable = false)
    private LocalDateTime reservedAt;

    @Version
    private Long version;

    @Builder
    public Reservation(User user, Schedule schedule, Seat seat, String reservationNumber) {
        this.user = user;
        this.schedule = schedule;
        this.seat = seat;
        this.reservationNumber = reservationNumber;
        this.status = ReservationStatus.RESERVED;
        this.reservedAt = LocalDateTime.now();
    }

    public void cancel() {
        this.status = ReservationStatus.CANCELLED;
    }

    public void expire() {
        this.status = ReservationStatus.EXPIRED;
    }

    public Long getUserId() {
        return user != null ? user.getId() : null;
    }

    public Long getScheduleId() {
        return schedule != null ? schedule.getId() : null;
    }

    public Long getSeatId() {
        return seat != null ? seat.getId() : null;
    }
} 