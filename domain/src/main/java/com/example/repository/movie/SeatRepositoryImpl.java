package com.example.repository.movie;

import com.example.entity.movie.Seat;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

import java.util.List;

import static com.example.entity.movie.QSeat.seat;

public class SeatRepositoryImpl implements SeatRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public SeatRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Seat> findAllByIdsWithLock(List<Long> ids) {
        return queryFactory.selectFrom(seat)
                .where(seat.id.in(ids))
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .fetch();
    }
}
