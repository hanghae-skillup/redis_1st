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
    private Long screenRoomId;

    @Builder
    public Reservation(LocalDateTime reserveTime, Long usersId, Long screenRoomId) {
        this.usersId = usersId;
        this.screenRoomId = screenRoomId;
    }

    public static Reservation create(Long usersId, Long screenRoomId) {
        return Reservation.builder()
                .usersId(usersId)
                .screenRoomId(screenRoomId)
                .build();
    }
}
