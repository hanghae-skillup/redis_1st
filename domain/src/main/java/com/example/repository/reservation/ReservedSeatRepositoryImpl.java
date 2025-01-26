package com.example.repository.reservation;

import com.example.entity.member.Member;
import com.example.entity.movie.Screening;
import com.example.entity.movie.Seat;
import com.example.entity.reservation.ReservedSeat;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.entity.reservation.QReservedSeat.reservedSeat;

public class ReservedSeatRepositoryImpl implements ReservedSeatRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ReservedSeatRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    @Transactional
    public List<ReservedSeat> findByScreeningAndSeats(Screening screening, List<Seat> seats) {
        return queryFactory.selectFrom(reservedSeat)
                .where(reservedSeat.reservation.screening.eq(screening).and(reservedSeat.seat.in(seats)))
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .fetch();
    }

    @Override
    public List<ReservedSeat> findAllByMemberId(Member member, Screening screening) {
        return queryFactory.selectFrom(reservedSeat)
                .where(reservedSeat.reservation.member.eq(member), reservedSeat.reservation.screening.eq(screening))
                .fetch();
    }
}
