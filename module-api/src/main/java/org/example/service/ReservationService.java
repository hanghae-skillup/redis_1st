package org.example.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.reservation.Reservation;
import org.example.domain.seat.Col;
import org.example.domain.seat.Row;
import org.example.domain.seat.Seat;
import org.example.dto.request.ReservationRequestDto;
import org.example.dto.request.ReservationSeat;
import org.example.repository.ReservationJpaRepository;
import org.example.repository.ScreenScheduleJpaRepository;
import org.example.repository.SeatJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ReservationService {
    private final ScreenScheduleJpaRepository screenScheduleJpaRepository;
    private final ReservationJpaRepository reservationJpaRepository;
    private final SeatJpaRepository seatJpaRepository;
    public void reserveMovie(ReservationRequestDto reservationRequestDto) {
        validateSeats(reservationRequestDto.reservationSeats());

        Reservation savedReservation = saveReservation(reservationRequestDto);
        saveSeats(reservationRequestDto, savedReservation);
    }

    private void validateSeats(List<ReservationSeat> seats) {
        validateSeatCount(seats);
        validateContinuousSeats(seats);
    }

    private static void validateSeatCount(List<ReservationSeat> seats) {
        if (seats.size() > 5) {
            throw new IllegalArgumentException("최대 5좌석만 예약 가능합니다.");
        }
    }

    private static void validateContinuousSeats(List<ReservationSeat> seats) {
        seats.sort((s1, s2) -> {
            if (s1.row().equals(s2.row())) {
                return s1.col().compareTo(s2.col());
            }

            return s1.row().compareTo(s2.row());
        });

        for (int i = 1; i < seats.size(); i++) {
            ReservationSeat prev = seats.get(i - 1);
            ReservationSeat current = seats.get(i);

            if (!prev.row().equals(current.row())) {
                throw new IllegalArgumentException("연속된 좌석만 예약할 수 있습니다. 행이 다릅니다.");
            }

            int prevCol = Col.valueOf(prev.col()).getColumn();
            int currentCol = Col.valueOf(current.col()).getColumn();
            if (currentCol != prevCol + 1) {
                throw new IllegalArgumentException("연속된 좌석만 예약할 수 있습니다. 열이 연속되지 않았습니다.");
            }
        }
    }

    private Reservation saveReservation(ReservationRequestDto reservationRequestDto) {
        Long screenRoomId = screenScheduleJpaRepository.findScreenRoomIdById(reservationRequestDto.screenScheduleId());
        Reservation reservation = Reservation.create(reservationRequestDto.usersId(), screenRoomId);
        return reservationJpaRepository.save(reservation);
    }

    private void saveSeats(ReservationRequestDto reservationRequestDto, Reservation savedReservation) {
        List<ReservationSeat> reservationSeats = reservationRequestDto.reservationSeats();
        for (ReservationSeat reservationSeat : reservationSeats) {
            Row row = Row.valueOf(reservationSeat.row());
            Col col = Col.valueOf(reservationSeat.col());
            Seat seat = Seat.create(row, col, savedReservation.getId());
            seatJpaRepository.save(seat);
        }
    }
}
