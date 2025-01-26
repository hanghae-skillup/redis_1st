package com.example.app.booking.out.persistence.repository;

import com.example.app.booking.out.persistence.entity.SeatEntity;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.app.booking.out.persistence.entity.QSeatEntity.seatEntity;

@RequiredArgsConstructor
public class SeatRepositoryCustomImpl implements SeatRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<SeatEntity> findAllByWithLock(Predicate predicate) {
        return queryFactory
                .select(seatEntity)
                .from(seatEntity)
                .where(predicate)
                .setLockMode(LockModeType.OPTIMISTIC)
                .fetch();
    }
}
