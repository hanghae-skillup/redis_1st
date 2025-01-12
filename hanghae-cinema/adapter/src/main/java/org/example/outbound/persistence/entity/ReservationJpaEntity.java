package org.example.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "reservations", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"screening_id", "seat_number"})
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationJpaEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screening_id", nullable = false)
    private ScreeningJpaEntity screening;

    private String userId;

    @Column(nullable = false, length = 5)
    private String seatNumber;

    @Column(nullable = false)
    private LocalDateTime reservedAt = LocalDateTime.now();

}