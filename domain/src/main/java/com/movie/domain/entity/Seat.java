package com.movie.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "seats")
public class Seat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "schedule_id", nullable = false)
    private Long scheduleId;

    @Column(name = "theater_id", nullable = false)
    private Long theaterId;

    @Column(nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    private String rowNumber;

    @Column(nullable = false)
    private Integer columnNumber;

    @Builder
    public Seat(Long id, Long scheduleId, Long theaterId, String seatNumber, String rowNumber, Integer columnNumber) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.theaterId = theaterId;
        this.seatNumber = seatNumber;
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }
} 