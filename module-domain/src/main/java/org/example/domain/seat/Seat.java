package org.example.domain.seat;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.example.domain.reservation.Reservation;
import org.example.entity.BaseEntity;
import org.hibernate.annotations.ColumnDefault;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints =
        {@UniqueConstraint(name = "UniqueRowAndColAndScreenRoom", columnNames = {"seat_row", "seat_col", "screenScheduleId"})})
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

    @Column(nullable = false)
    private Long screenScheduleId;

    @Builder
    public Seat(Row row, Col col, Long reservationId, Long screenScheduleId) {
        this.row = row;
        this.col = col;
        this.reservationId = reservationId;
        this.screenScheduleId = screenScheduleId;
    }

    public static Seat create(Row row, Col col, Long reservationId, Long screenScheduleId) {
        return Seat.builder()
                .row(row)
                .col(col)
                .reservationId(reservationId)
                .screenScheduleId(screenScheduleId)
                .build();
    }
}
