package org.example.service.reservation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.domain.reservation.Reservation;
import org.example.domain.reservationseat.ReservationSeat;
import org.example.domain.seat.Seat;
import org.example.dto.SeatsDto;
import org.example.dto.request.ReservationRequestDto;
import org.example.exception.SeatException;
import org.example.repository.ReservationJpaRepository;
import org.example.repository.ReservationSeatRepository;
import org.example.repository.SeatJpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.example.baseresponse.BaseResponseStatus.ALREADY_RESERVED_SEAT_ERROR;
import static org.example.baseresponse.BaseResponseStatus.UNAVAILABLE_SEAT_ERROR;

@Service
@AllArgsConstructor
public class SaveReservationService {
    private final ReservationJpaRepository reservationJpaRepository;
    private final ReservationSeatRepository reservationSeatRepository;

    @Transactional
    public void saveReservationWithTransaction(ReservationRequestDto reservationRequestDto, List<Seat> seats) {
        // 예약된 좌석인지 검증
        for (Seat seat : seats) {
            boolean isReserved = reservationSeatRepository.findReservedSeatBySeatId(reservationRequestDto.screenScheduleId(), seat.getId()).isPresent();
            if (isReserved) {
                throw new SeatException(ALREADY_RESERVED_SEAT_ERROR);
            }
        }

        Long reservationId = saveReservation(reservationRequestDto.usersId(), reservationRequestDto.screenScheduleId());
        saveReservationSeats(seats, reservationId);
    }

    private Long saveReservation(Long userId, Long screenScheduleId) {
        Reservation reservation = Reservation.of(userId, screenScheduleId);
        Reservation savedReservation = reservationJpaRepository.save(reservation);
        return savedReservation.getId();
    }

    private void saveReservationSeats(List<Seat> seats, Long reservationId) {
        for (Seat seat : seats) {
            ReservationSeat reservationSeat = ReservationSeat.of(reservationId, seat.getId());
            reservationSeatRepository.save(reservationSeat);
        }
    }
}
