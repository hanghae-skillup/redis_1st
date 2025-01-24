package org.example.domain.seat;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.example.domain.reservation.Reservation;
import org.example.entity.BaseEntity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id", nullable = false)
    private Long id;

    @Column(nullable = false, name = "seat_row")
    @Enumerated(EnumType.STRING)
    private Row row;

    @Column(nullable = false, name = "seat_col")
    @Enumerated(EnumType.STRING)
    private Col col;

    @Column(nullable = false)
    private Long reservationId;

    @Builder
    public Seat(Row row, Col col, Long reservationId) {
        this.row = row;
        this.col = col;
        this.reservationId = reservationId;
    }

    public static Seat create(Row row, Col col, Long reservationId) {
        return Seat.builder()
                .row(row)
                .col(col)
                .reservationId(reservationId)
                .build();
    }
}
