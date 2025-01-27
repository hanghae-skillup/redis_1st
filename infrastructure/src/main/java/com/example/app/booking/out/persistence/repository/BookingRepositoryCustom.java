package com.example.app.booking.out.persistence.repository;

import com.example.app.booking.out.persistence.entity.BookingEntity;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface BookingRepositoryCustom {

    List<BookingEntity> findAllBy(Predicate predicate);
}
