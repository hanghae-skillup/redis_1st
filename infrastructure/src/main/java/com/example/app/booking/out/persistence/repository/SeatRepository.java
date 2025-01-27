package com.example.app.booking.out.persistence.repository;

import com.example.app.booking.out.persistence.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<SeatEntity, Long>, SeatRepositoryCustom {

    List<SeatEntity> findAllByBookingIdIn(List<Long> bookingIds);
}
