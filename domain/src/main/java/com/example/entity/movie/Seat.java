package com.example.entity.movie;

import com.example.entity.reservation.ReservedSeat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Long id;

    private String seatNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @OneToMany(mappedBy = "seat")
    private List<ReservedSeat> reservedSeats = new ArrayList<>();

    @Builder
    public Seat(String seatNumber, Theater theater) {
        this.seatNumber = seatNumber;
        this.theater = theater;
    }

    public String getRow() {
        return seatNumber.substring(0, 1);
    }

    public int getColumn() {
        return Integer.parseInt(seatNumber.substring(1));
    }
}