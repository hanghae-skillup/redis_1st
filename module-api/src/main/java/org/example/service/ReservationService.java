package org.example.service;

import jakarta.persistence.OptimisticLockException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.reservation.Reservation;
import org.example.domain.seat.Col;
import org.example.domain.seat.Row;
import org.example.domain.seat.Seat;
import org.example.dto.ReservedSeats;
import org.example.dto.request.ReservationRequestDto;
import org.example.dto.request.ReservationSeat;
import org.example.repository.ReservationJpaRepository;
import org.example.repository.ScreenScheduleJpaRepository;
import org.example.repository.SeatJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationJpaRepository reservationJpaRepository;
    private final SeatJpaRepository seatJpaRepository;
    private final ScreenScheduleJpaRepository screenScheduleJpaRepository;

    @Transactional
    public void reserveMovie(ReservationRequestDto reservationRequestDto) {
        validateSeats(reservationRequestDto.reservationSeats());

        Long screenRoomId = screenScheduleJpaRepository.findScreenRoomIdById(reservationRequestDto.screenScheduleId());
        validateUserReserveSeats(reservationRequestDto.reservationSeats(), reservationRequestDto.usersId(), reservationRequestDto.screenScheduleId());

        try {
            List<Seat> seats = new ArrayList<>();
            for (ReservationSeat reservationSeat : reservationRequestDto.reservationSeats()) {
                Row row = Row.valueOf(reservationSeat.row());
                Col col = Col.valueOf(reservationSeat.col());

                Seat seat = seatJpaRepository.findSeats(screenRoomId, row, col)
                        .orElseThrow(() -> new IllegalStateException("예약할 수 없는 좌석입니다."));

                seats.add(seat);
            }
            saveReservation(reservationRequestDto.usersId(), reservationRequestDto.screenScheduleId(), seats);
        } catch (OptimisticLockException e) {
            throw new IllegalArgumentException("다른 사용자가 이미 예약을 진행 중입니다. 다시 시도해 주세요.");
        }
    }

    private void validateSeats(List<ReservationSeat> seats) {
        validateSeatCount(seats);
        validateContinuousSeats(seats);
    }

    private void validateSeatCount(List<ReservationSeat> seats) {
        if (seats.size() > 5) {
            throw new IllegalArgumentException("최대 5좌석만 예약 가능합니다.");
        }
    }

    private void validateContinuousSeats(List<ReservationSeat> seats) {
        seats.sort(Comparator.comparing(ReservationSeat::col));

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

    private void validateUserReserveSeats(List<ReservationSeat> reservationSeats, Long userId, Long screenScheduleId) {
        List<ReservedSeats> reservedSeatsByUserId = reservationJpaRepository.findReservedSeatByUserIdAndScreenScheduleId(userId, screenScheduleId);
        if (reservedSeatsByUserId.isEmpty()) {
            return;
        }

        isInMaxCount(reservationSeats, reservedSeatsByUserId); // 예약하려는 좌석이 5개 이상인지
        containsReservedSeat(reservationSeats, reservedSeatsByUserId); // 이미 예약된 좌석과 겹치는지
        isSameRow(reservationSeats, reservedSeatsByUserId); // 좌석이 같은 행에 있는지
        isContinuousCol(reservationSeats, reservedSeatsByUserId); // 좌석이 연속된 열인지
    }

    private void isInMaxCount(List<ReservationSeat> reservationSeats, List<ReservedSeats> reservedSeatsByUserId) {
        log.info(String.valueOf(reservedSeatsByUserId.size()));
        log.info(String.valueOf(reservationSeats.size()));
        if (reservationSeats.size() + reservedSeatsByUserId.size() > 5) {
            throw new IllegalArgumentException("최대 5좌석만 예약 가능합니다.");
        }
    }

    private void containsReservedSeat(List<ReservationSeat> reservationSeats, List<ReservedSeats> reservedSeatsByUserId) {
        for (ReservedSeats reservedSeats : reservedSeatsByUserId) {
            for (ReservationSeat reservationSeat : reservationSeats) {
                if (reservedSeats.getRow().equals(Row.valueOf(reservationSeat.row()))
                        && reservedSeats.getCol().equals(Col.valueOf(reservationSeat.col()))) {
                    throw new IllegalArgumentException("이미 예약된 좌석이 포함되어 있습니다");
                }
            }
        }
    }

    private void isSameRow(List<ReservationSeat> reservationSeats, List<ReservedSeats> reservedSeatsByUserId) {
        Row row = reservedSeatsByUserId.get(0).getRow();
        for (ReservationSeat reservationSeat : reservationSeats) {
            if (!row.equals(Row.valueOf(reservationSeat.row()))) {
                throw new IllegalArgumentException("연속된 좌석만 예약할 수 있습니다. 행이 다릅니다.");
            }
        }
    }

    private void isContinuousCol(List<ReservationSeat> reservationSeats, List<ReservedSeats> reservedSeatsByUserId) {
        Col reservedCol = reservedSeatsByUserId.get(reservedSeatsByUserId.size() - 1).getCol();
        Col reservationCol = Col.valueOf(reservationSeats.get(0).col());
        if (reservationCol.getColumn() != reservedCol.getColumn()+1) {
            throw new IllegalArgumentException("연속된 좌석만 예약할 수 있습니다. 열이 연속되지 않았습니다.");
        }
    }

    private void saveReservation(Long userId, Long screenScheduleId, List<Seat> seats) {
        for (Seat seat : seats) {
            boolean isReserved = reservationJpaRepository.existsByUsersIdAndScreenScheduleIdAndSeatId(userId, screenScheduleId, seat.getId());
            if (isReserved) {
                throw new IllegalStateException("예약할 수 없는 좌석입니다.");
            }

            Reservation reservation = Reservation.create(userId, screenScheduleId, seat.getId());
            reservationJpaRepository.save(reservation);
        }
    }
}
