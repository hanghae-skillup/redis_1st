package org.example.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.config.RedissonLockUtil;
import org.example.domain.reservation.Reservation;
import org.example.domain.reservationseat.ReservationSeat;
import org.example.domain.seat.Col;
import org.example.domain.seat.Row;
import org.example.domain.seat.Seat;
import org.example.dto.SeatsDto;
import org.example.dto.request.ReservationRequestDto;
import org.example.exception.SeatException;
import org.example.repository.ReservationJpaRepository;
import org.example.repository.ReservationSeatRepository;
import org.example.repository.ScreenScheduleJpaRepository;
import org.example.repository.SeatJpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.example.baseresponse.BaseResponseStatus.ALREADY_RESERVED_SEAT_ERROR;
import static org.example.baseresponse.BaseResponseStatus.UNAVAILABLE_SEAT_ERROR;

@Slf4j
@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationJpaRepository reservationJpaRepository;
    private final SeatJpaRepository seatJpaRepository;
    private final ScreenScheduleJpaRepository screenScheduleJpaRepository;
    private final ReservationSeatRepository reservationSeatRepository;
    private final RedissonLockUtil redissonLockUtil;

    public void reserveMovie(ReservationRequestDto reservationRequestDto) {
        List<SeatsDto> reservationSeats = new ArrayList<>(
                reservationRequestDto.reservationSeats().stream()
                        .map(seat -> new SeatsDto(Row.valueOf(seat.row()), Col.valueOf(seat.col())))
                        .toList()
        );

        Long screenRoomId = screenScheduleJpaRepository.findScreenRoomIdById(reservationRequestDto.screenScheduleId());

        // 예약하려는 좌석 검증
        validateSeats(reservationSeats);

        // 사용자가 동일한 상영에 대해 예약한 좌석 검증
        validateUserReserveSeats(reservationSeats, reservationRequestDto.usersId(), reservationRequestDto.screenScheduleId());

        // 개별 좌석별 락 키 생성
        List<String> lockKeys = reservationRequestDto.reservationSeats().stream()
                .map(seat -> "lock:seat:" + screenRoomId + ":" + seat.row() + ":" + seat.col())
                .toList();

        // Redisson MultiLock 적용 (여러 개의 좌석을 동시에 보호)
        redissonLockUtil.executeWithMultiLock(lockKeys, 5, 10, () -> {
            saveReservationWithTransaction(reservationRequestDto, reservationSeats, screenRoomId);
            return null;
        });
    }

    @Transactional
    public void saveReservationWithTransaction(ReservationRequestDto reservationRequestDto, List<SeatsDto> reservationSeats, Long screenRoomId) {
        // 좌석에 락을 걸고 저장할 좌석들 반환
        List<Seat> seats = validateReservedSeats(screenRoomId, reservationSeats);

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

    private void validateSeats(List<SeatsDto> seats) {
        Seat.validateCountExceeded(seats.size());
        Seat.validateContinuousSeats(seats);
    }

    private void validateUserReserveSeats(List<SeatsDto> reservationSeats, Long userId, Long screenScheduleId) {
        List<SeatsDto> reservedSeats = reservationSeatRepository.findReservedSeatByUserIdAndScreenScheduleId(userId, screenScheduleId);
        if (reservedSeats.isEmpty()) {
            return;
        }

        Seat.validateCountExceeded(reservationSeats.size() + reservedSeats.size()); // 예약하려는 좌석이 5개 이상인지
        Seat.containsReservedSeat(reservationSeats, reservedSeats); // 이미 예약된 좌석과 겹치는지
        Seat.isSameRow(reservationSeats, reservedSeats); // 좌석이 같은 행에 있는지
        Seat.isContinuousCol(reservationSeats, reservedSeats); // 좌석이 연속된 열인지
    }

    private List<Seat> validateReservedSeats(Long screenRoomId, List<SeatsDto> reservationSeats) {
        List<Seat> seats = new ArrayList<>();
        for (SeatsDto reservationSeat : reservationSeats) {
            Seat seat = seatJpaRepository.findSeats(screenRoomId, reservationSeat.getRow(), reservationSeat.getCol())
                    .orElseThrow(() -> new SeatException(UNAVAILABLE_SEAT_ERROR));

            seats.add(seat);
        }
        return seats;
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