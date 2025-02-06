package com.example.domain.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
public class ReservedSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Reservation reservation;

    @ManyToOne
    private ScreeningSeat screeningSeat;


    protected ReservedSeat() {}

    private ReservedSeat(Reservation reservation, ScreeningSeat screeningSeat) {
        this.reservation = reservation;
        this.screeningSeat = screeningSeat;
    }

    public static ReservedSeat of(Reservation reservation, ScreeningSeat screeningSeat) {
        return new ReservedSeat(reservation, screeningSeat);
    }

}
