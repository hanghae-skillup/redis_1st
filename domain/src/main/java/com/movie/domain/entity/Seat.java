package com.movie.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "seat")
public class Seat extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String seatNumber;

    @Column(name = "theater_id", nullable = false)
    private Long theaterId;

    @Column(name = "row_number", nullable = false)
    private Integer row;

    @Column(name = "column_number", nullable = false)
    private Integer column;

    public Seat(String seatNumber, Long theaterId, Integer row, Integer column) {
        this.seatNumber = seatNumber;
        this.theaterId = theaterId;
        this.row = row;
        this.column = column;
    }
} 