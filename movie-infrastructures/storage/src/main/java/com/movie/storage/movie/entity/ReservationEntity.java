package com.movie.storage.movie.entity;

import com.movie.storage.BaseEntity;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reservation")
public class ReservationEntity extends BaseEntity {

    @EmbeddedId
    private ReservationComplexIds id;

    private Long userId;

    private LocalDateTime reservedAt;

    public void makeReservation(Long scheduleId, Long seatId, Long userId) {
        this.id = ReservationComplexIds.of(scheduleId, seatId);
        this.userId = userId;
        this.reservedAt = LocalDateTime.now();
    }

}
