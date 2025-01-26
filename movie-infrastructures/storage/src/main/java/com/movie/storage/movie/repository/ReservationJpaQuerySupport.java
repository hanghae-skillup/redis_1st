package com.movie.storage.movie.repository;

import com.movie.storage.movie.dto.payload.QReservationPayload_Get;
import com.movie.storage.movie.dto.payload.ReservationPayload;
import com.movie.storage.movie.entity.ReservationEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.movie.storage.movie.entity.QReservationEntity.reservationEntity;

@Component
public class ReservationJpaQuerySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public ReservationJpaQuerySupport(JPAQueryFactory queryFactory) {
        super(ReservationEntity.class);
        this.queryFactory = queryFactory;
    }

    public List<ReservationPayload.Get> getReservations(Long scheduleId, List<Long> seatIds) {
        return queryFactory.select(
                        new QReservationPayload_Get(
                                reservationEntity.id.scheduleId, reservationEntity.id.seatId,
                                reservationEntity.userId, reservationEntity.reservedAt
                        )
                ).from(reservationEntity)
                .where(
                        reservationEntity.id.scheduleId.eq(scheduleId)
                                .and(reservationEntity.id.seatId.in(seatIds))
                )
                .setLockMode(LockModeType.OPTIMISTIC)
                .fetch();
    }

    public List<ReservationEntity> getReservationEntities(Long scheduleId, List<Long> seatIds) {
        return queryFactory.selectFrom(reservationEntity)
                .where(
                        reservationEntity.id.scheduleId.eq(scheduleId)
                                .and(reservationEntity.id.seatId.in(seatIds))
                )
                .setLockMode(LockModeType.OPTIMISTIC)
                .fetch();
    }

}
