package com.example.app.booking.out.persistence.repository;

import com.example.app.booking.out.persistence.entity.SeatEntity;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface SeatRepositoryCustom {
    List<SeatEntity> findAllByWithLock(Predicate predicate);
}
