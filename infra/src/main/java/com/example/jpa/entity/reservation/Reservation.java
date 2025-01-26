package com.example.jpa.entity.reservation;

import com.example.jpa.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long seatId;

    private Long screeningId;

    public void reserve(Long userId, Long seatId, Long screeningId) {
        this.userId = userId;
        this.seatId = seatId;
        this.screeningId = screeningId;
    }
}
