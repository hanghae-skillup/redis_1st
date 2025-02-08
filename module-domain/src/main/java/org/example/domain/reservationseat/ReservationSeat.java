package org.example.domain.reservationseat;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.reservation.Reservation;
import org.example.domain.seat.Col;
import org.example.domain.seat.Row;
import org.example.dto.SeatsDto;
import org.example.entity.BaseEntity;
import org.example.exception.SeatException;

import java.util.List;

import static org.example.baseresponse.BaseResponseStatus.*;
import static org.example.baseresponse.BaseResponseStatus.SEAT_COLUMN_DISCONTINUITY_ERROR;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationSeat extends BaseEntity {
    private static final int MAX_SEAT_COUNT = 5;

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

    public static void validateCountExceeded(List<SeatsDto> reservationSeats, List<SeatsDto> seatsDtoByUserId) {
        if (reservationSeats.size()+seatsDtoByUserId.size() > MAX_SEAT_COUNT) {
            throw new SeatException(MAX_SEATS_EXCEEDED_ERROR);
        }
    }

    public static void containsReservedSeat(List<SeatsDto> reservationSeats, List<SeatsDto> seatsDtoByUserId) {
        for (SeatsDto seatsDto : seatsDtoByUserId) {
            for (SeatsDto reservationSeat : reservationSeats) {
                if (seatsDto.getRow().equals(reservationSeat.getRow())
                        && seatsDto.getCol().equals(reservationSeat.getCol())) {
                    throw new SeatException(ALREADY_RESERVED_SEAT_ERROR);
                }
            }
        }
    }

    public static void isSameRow(List<SeatsDto> reservationSeats, List<SeatsDto> seatsDtoByUserId) {
        Row row = seatsDtoByUserId.get(0).getRow();
        for (SeatsDto reservationSeat : reservationSeats) {
            if (!row.equals(reservationSeat.getRow())) {
                throw new SeatException(SEAT_ROW_DISCONTINUITY_ERROR);
            }
        }
    }

    public static void isContinuousCol(List<SeatsDto> reservationSeats, List<SeatsDto> seatsDtoByUserId) {
        Col reservedCol = seatsDtoByUserId.get(seatsDtoByUserId.size() - 1).getCol();
        Col reservationCol = reservationSeats.get(0).getCol();
        if (reservationCol.getColumn() != reservedCol.getColumn()+1) {
            throw new SeatException(SEAT_COLUMN_DISCONTINUITY_ERROR);
        }
    }
}
