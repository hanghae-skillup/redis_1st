package com.example.repository.reservation;

import com.example.entity.movie.Screening;
import com.example.entity.movie.Seat;
import com.example.entity.reservation.ReservedSeat;
import com.example.entity.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.example.entity.reservation.QReservedSeat.reservedSeat;

public class ReservedSeatRepositoryImpl implements ReservedSeatRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ReservedSeatRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<ReservedSeat> findByScreeningAndSeats(Screening screening, List<Seat> seats) {
        return queryFactory.selectFrom(reservedSeat)
                .where(reservedSeat.reservation.screening.eq(screening).and(reservedSeat.seat.in(seats)))
                .fetch();
    }

    @Override
    public List<ReservedSeat> findAllByUserId(User user, Screening screening) {
        return queryFactory.selectFrom(reservedSeat)
                .where(reservedSeat.reservation.user.eq(user), reservedSeat.reservation.screening.eq(screening))
                .fetch();
    }
}
