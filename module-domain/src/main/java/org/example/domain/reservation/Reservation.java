package org.example.domain.reservation;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.entity.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id", nullable = false)
    private Long id;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime reserveTime;

    @Column(nullable = false)
    private Long usersId;

    @Column(nullable = false)
    private Long screenScheduleId;

    @Column(nullable = false)
    private Long seatId;

    @Builder
    public Reservation(Long usersId, Long screenScheduleId, Long seatId) {
        this.usersId = usersId;
        this.screenScheduleId = screenScheduleId;
        this.seatId = seatId;
    }

    public static Reservation create(Long usersId, Long screenScheduleId, Long seatId) {
        return Reservation.builder()
                .usersId(usersId)
                .screenScheduleId(screenScheduleId)
                .seatId(seatId)
                .build();
    }
}
