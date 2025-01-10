package com.example.entity.reservation;

import com.example.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Entity
public class Seat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long theaterId;

    @Nullable
    private Long userId;

    private char seatRow;

    private Long seatCol;

    private Boolean isReservation;

    private LocalDateTime reservationTime;
}
