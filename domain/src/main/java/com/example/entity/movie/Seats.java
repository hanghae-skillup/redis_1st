package com.example.entity.movie;

import java.util.List;

public class Seats {

    private static final int MAX_ALLOWED_SEATS = 5;

    private final List<Seat> seats;

    public Seats(List<Seat> seats) {
        this.seats = seats;
    }

    public boolean isSizeExceedingLimit() {
        return this.seats.size() > MAX_ALLOWED_SEATS;
    }
}
