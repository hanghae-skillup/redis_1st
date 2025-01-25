package com.example.jpa.entity.theater;

import com.example.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Entity
@Getter
public class Seat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long theaterId;

    @Nullable
    private Long reservationId;

    private String position;

    public void reserve(Long reservationId) {
        this.reservationId = reservationId;
    }
}
