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

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    @Column(name = "theater_id", nullable = false)
    private Long theaterId;

    @Column(name = "seat_row", nullable = false)
    private Integer seatRow;

    @Column(name = "seat_column", nullable = false)
    private Integer seatColumn;

    public Seat(Long theaterId, String seatNumber, Integer seatRow, Integer seatColumn) {
        this.theaterId = theaterId;
        this.seatNumber = seatNumber;
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
    }
} 