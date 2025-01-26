package com.example.repository.reservation;

import com.example.entity.reservation.Reservation;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.example.entity.reservation.QReservation.reservation;
import static com.example.entity.reservation.QReservedSeat.reservedSeat;

public class ReservationRepositoryImpl implements ReservationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ReservationRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Reservation> findByScreeningIdWithReservedSeats(Long screeningId) {
        return queryFactory.selectFrom(reservation)
                .join(reservedSeat).fetchJoin()
                .fetch();
    }
}
