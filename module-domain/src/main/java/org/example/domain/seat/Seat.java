package org.example.domain.seat;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.reservationseat.ReservationSeat;
import org.example.dto.SeatsDto;
import org.example.entity.BaseEntity;
import org.example.exception.SeatException;

import java.util.Comparator;
import java.util.List;

import static org.example.baseresponse.BaseResponseStatus.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class Seat extends BaseEntity {
    private static final int MAX_SEAT_COUNT = 5;

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
    private Long screenRoomId;

    public static void validateCountExceeded(int seatSize) {
        if (seatSize > MAX_SEAT_COUNT) {
            throw new SeatException(MAX_SEATS_EXCEEDED_ERROR);
        }
    }

    public static void validateContinuousSeats(List<SeatsDto> seats) {
        seats.sort(Comparator.comparing(seat -> seat.getCol().getColumn()));

        for (int i = 1; i < seats.size(); i++) {
            SeatsDto prev = seats.get(i - 1);
            SeatsDto current = seats.get(i);

            if (!prev.getRow().getRow().equals(current.getRow().getRow())) {
                throw new SeatException(SEAT_ROW_DISCONTINUITY_ERROR);
            }

            int prevCol = prev.getCol().getColumn();
            int currentCol = current.getCol().getColumn();
            if (currentCol != prevCol + 1) {
                throw new SeatException(SEAT_COLUMN_DISCONTINUITY_ERROR);
            }
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
