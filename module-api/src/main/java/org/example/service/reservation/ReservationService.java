package org.example.service.reservation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.config.RedissonLockUtil;
import org.example.domain.reservationseat.ReservationSeat;
import org.example.domain.seat.Col;
import org.example.domain.seat.Row;
import org.example.domain.seat.Seat;
import org.example.dto.SeatsDto;
import org.example.dto.request.ReservationRequestDto;
import org.example.exception.SeatException;
import org.example.repository.ReservationSeatRepository;
import org.example.repository.ScreenScheduleJpaRepository;
import org.example.repository.SeatJpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.example.baseresponse.BaseResponseStatus.UNAVAILABLE_SEAT_ERROR;

@Slf4j
@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationSeatRepository reservationSeatRepository;
    private final ScreenScheduleJpaRepository screenScheduleJpaRepository;
    private final RedissonLockUtil redissonLockUtil;
    private final SaveReservationService saveReservationService;
    private final SeatJpaRepository seatJpaRepository;

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

        // 좌석들 반환
        List<Seat> seats = validateReservedSeats(screenRoomId, reservationSeats);

        // 개별 좌석별 락 키 생성
        List<String> lockKeys = reservationRequestDto.reservationSeats().stream()
                .map(seat -> "lock:seat:" + reservationRequestDto.screenScheduleId() + ":" + seat.row() + ":" + seat.col())
                .toList();

        // Redisson MultiLock 적용 (여러 개의 좌석을 동시에 보호)
        redissonLockUtil.executeWithMultiLock(lockKeys, 5, 10, () -> {
            saveReservationService.saveReservationWithTransaction(reservationRequestDto.usersId(), reservationRequestDto.screenScheduleId(), seats);
            return null;
        });
    }

    public List<Seat> validateReservedSeats(Long screenRoomId, List<SeatsDto> reservationSeats) {
        List<Seat> seats = new ArrayList<>();
        for (SeatsDto reservationSeat : reservationSeats) {
            Seat seat = seatJpaRepository.findSeats(screenRoomId, reservationSeat.getRow(), reservationSeat.getCol())
                    .orElseThrow(() -> new SeatException(UNAVAILABLE_SEAT_ERROR));

            seats.add(seat);
        }
        return seats;
    }

    private void validateSeats(List<SeatsDto> seats) {
        Seat.validateSeatCount(seats.size());
        Seat.validateContinuousSeats(seats);
    }

    private void validateUserReserveSeats(List<SeatsDto> reservationSeats, Long userId, Long screenScheduleId) {
        List<SeatsDto> reservedSeats = reservationSeatRepository.findReservedSeatByUserIdAndScreenScheduleId(userId, screenScheduleId);
        if (reservedSeats.isEmpty()) {
            return;
        }

        ReservationSeat.validateCountExceeded(reservationSeats, reservedSeats); // 예약하려는 좌석이 5개 이상인지
        ReservationSeat.containsReservedSeat(reservationSeats, reservedSeats); // 이미 예약된 좌석과 겹치는지
        ReservationSeat.isSameRow(reservationSeats, reservedSeats); // 좌석이 같은 행에 있는지
        ReservationSeat.isContinuousCol(reservationSeats, reservedSeats); // 좌석이 연속된 열인지
    }
}