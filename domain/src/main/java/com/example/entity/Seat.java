package com.example.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Long id;

    private String seatNumber;

    private boolean reserved;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theater theater;

    public Seat(String seatNumber, Theater theater) {
        this.seatNumber = seatNumber;
        this.theater = theater;
        this.reserved = false;
    }

    public void reserve() {
        this.reserved = true;
    }

    public void cancelReservation() {
        this.reserved = false;
    }
}