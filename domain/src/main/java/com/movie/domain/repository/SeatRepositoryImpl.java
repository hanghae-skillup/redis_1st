package com.movie.domain.repository;

import com.movie.domain.entity.Schedule;
import com.movie.domain.entity.Seat;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.movie.domain.entity.QReservation.reservation;
import static com.movie.domain.entity.QSeat.seat;

@Repository
@RequiredArgsConstructor
public class SeatRepositoryImpl implements SeatRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Seat> findReservedSeats(Schedule schedule) {
        return queryFactory.select(seat)
                .from(seat)
                .join(reservation).on(seat.in(reservation.seats))
                .where(reservation.schedule.eq(schedule))
                .fetch();
    }
} 