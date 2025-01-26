package org.example.domain.screenSchedule;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.entity.BaseEntity;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ScreenSchedule", indexes = {
        @Index(name = "movieId_idx", columnList = "movieId"),
        @Index(name = "screenRoomId_idx", columnList = "screenRoomId"),
})
public class ScreenSchedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screen_schedule_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private Long movieId;

    @Column(nullable = false)
    private Long screenRoomId;
}
