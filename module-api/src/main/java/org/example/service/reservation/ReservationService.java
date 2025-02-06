package org.example.service.reservation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.config.RedissonLockUtil;
import org.example.domain.seat.Col;
import org.example.domain.seat.Row;
import org.example.domain.seat.Seat;
import org.example.dto.SeatsDto;
import org.example.dto.request.ReservationRequestDto;
import org.example.repository.ReservationSeatRepository;
import org.example.repository.ScreenScheduleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationSeatRepository reservationSeatRepository;
    private final ScreenScheduleJpaRepository screenScheduleJpaRepository;
    private final RedissonLockUtil redissonLockUtil;
    private final SaveReservationService saveReservationService;

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
                .map(seat -> "lock:seat:" + reservationRequestDto.screenScheduleId() + ":" + seat.row() + ":" + seat.col())
                .toList();

        // Redisson MultiLock 적용 (여러 개의 좌석을 동시에 보호)
        redissonLockUtil.executeWithMultiLock(lockKeys, 5, 10, () -> {
            saveReservationService.saveReservationWithTransaction(reservationRequestDto, reservationSeats, screenRoomId);
            return null;
        });
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
}