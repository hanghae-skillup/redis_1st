package com.example.reservation.validator;

import com.example.entity.member.Member;
import com.example.entity.movie.Screening;
import com.example.entity.movie.Seats;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReservationValidationResult {
    private final Member member;
    private final Screening screening;
    private final Seats seats;

    @Builder
    public ReservationValidationResult(Member member, Screening screening, Seats seats) {
        this.member = member;
        this.screening = screening;
        this.seats = seats;
    }
}