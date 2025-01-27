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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @Column(name = "row_number", nullable = false)
    private Integer row;

    @Column(name = "column_number", nullable = false)
    private Integer column;

    public Seat(String seatNumber, Theater theater, Integer row, Integer column) {
        this.seatNumber = seatNumber;
        this.theater = theater;
        this.row = row;
        this.column = column;
    }

    public Long getTheaterId() {
        return theater != null ? theater.getId() : null;
    }
} 