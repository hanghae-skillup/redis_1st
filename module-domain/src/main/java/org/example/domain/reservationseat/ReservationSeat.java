package org.example.domain.reservationseat;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.reservation.Reservation;
import org.example.entity.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationSeat extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_seat_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long reservationId;

    @Column(nullable = false)
    private Long seatId;

    @Builder
    public ReservationSeat(Long reservationId, Long seatId) {
        this.reservationId = reservationId;
        this.seatId = seatId;
    }

    public static ReservationSeat of(Long reservationId, Long seatId) {
        return ReservationSeat.builder()
                .reservationId(reservationId)
                .seatId(seatId)
                .build();
    }
}
