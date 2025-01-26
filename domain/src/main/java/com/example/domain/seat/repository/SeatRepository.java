package com.example.domain.seat.repository;

import com.example.domain.seat.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Long> {
}