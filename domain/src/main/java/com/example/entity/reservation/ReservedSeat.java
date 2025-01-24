package com.example.entity.reservation;

import com.example.entity.BaseEntity;
import com.example.entity.movie.Seat;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.PROTECTED;


@Entity
@NoArgsConstructor(access = PROTECTED)
public class ReservedSeat extends BaseEntity {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(columnDefinition = "INT UNSIGNED")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reservedSeats")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "reservedSeats")
    private Seat seat;

    public ReservedSeat(Reservation reservation, Seat seat) {
        this.reservation = reservation;
        this.seat = seat;
    }
}
