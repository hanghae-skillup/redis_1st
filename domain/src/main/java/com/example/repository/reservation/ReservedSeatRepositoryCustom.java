package com.example.repository.reservation;

import com.example.entity.movie.Screening;
import com.example.entity.movie.Seat;
import com.example.entity.reservation.ReservedSeat;
import com.example.entity.member.Member;

import java.util.List;

public interface ReservedSeatRepositoryCustom {
    List<ReservedSeat> findByScreeningAndSeats(Screening screening, List<Seat> seats);

    List<ReservedSeat> findAllByMemberId(Member member, Screening screening);
}
