package com.movie.domain.repository;

import com.movie.domain.entity.Schedule;
import com.movie.domain.entity.Seat;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.movie.domain.entity.QReservation.reservation;
import static com.movie.domain.entity.QSeat.seat;

@RequiredArgsConstructor
public class SeatRepositoryImpl implements SeatRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Seat> findAvailableSeats(Schedule schedule) {
        return queryFactory
                .selectFrom(seat)
                .where(seat.theaterId.eq(schedule.getTheaterId())
                        .and(seat.id.notIn(
                                queryFactory.select(reservation.seatId)
                                        .from(reservation)
                                        .where(reservation.scheduleId.eq(schedule.getId()))
                        )))
                .fetch();
    }
} 