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

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long scheduleId;

    @Column(nullable = false)
    private Long seatId;

    @Column(nullable = false)
    private String reservationNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;

    @Column(nullable = false)
    private LocalDateTime reservedAt;

    @Builder
    public Reservation(Long userId, Long scheduleId, Long seatId, String reservationNumber) {
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.seatId = seatId;
        this.reservationNumber = reservationNumber;
        this.status = ReservationStatus.RESERVED;
        this.reservedAt = LocalDateTime.now();
    }

    public void cancel() {
        this.status = ReservationStatus.CANCELLED;
    }
} 