package com.movie.storage.movie.entity;

import com.movie.storage.BaseEntity;
import jakarta.persistence.*;
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

    @Version
    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer version;

    public ReservationEntity(ReservationComplexIds id, Long userId, LocalDateTime reservedAt) {
        this.id = id;
        this.userId = userId;
        this.reservedAt = reservedAt;
    }

    public void makeReservation(Long userId) {
        this.userId = userId;
        this.reservedAt = LocalDateTime.now();
    }

}
