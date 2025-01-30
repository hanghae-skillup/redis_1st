package com.example.app.booking.out.persistence.repository;

import static com.example.app.booking.out.persistence.entity.QBookingEntity.bookingEntity;

import com.example.app.booking.out.persistence.entity.BookingEntity;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BookingRepositoryCustomImpl implements BookingRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<BookingEntity> findAllBy(Predicate predicate) {
        return queryFactory
                .select(bookingEntity)
                .from(bookingEntity)
                .where(predicate)
                .fetch();
    }
}
