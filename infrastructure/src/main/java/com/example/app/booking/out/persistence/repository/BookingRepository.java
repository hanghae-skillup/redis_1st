package com.example.app.booking.out.persistence.repository;

import com.example.app.booking.out.persistence.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity, Long>, BookingRepositoryCustom {
}
