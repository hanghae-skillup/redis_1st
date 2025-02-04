package project.redis.application.reservation.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import project.redis.application.reservation.port.inbound.ReservationCommandUseCase;
import project.redis.application.reservation.port.inbound.ReserveCommandParam;
import project.redis.application.reservation.port.outbound.ReservationCommandPort;
import project.redis.application.reservation.port.outbound.ReservationLockPort;
import project.redis.application.reservation.port.outbound.ReservationQueryPort;
import project.redis.application.screening.port.outbound.ScreeningQueryPort;
import project.redis.application.seat.port.outbound.SeatQueryPort;
import project.redis.common.exception.DataInvalidException;
import project.redis.common.exception.ErrorCode;
import project.redis.domain.reservation.Reservation;
import project.redis.domain.screening.Screening;
import project.redis.domain.seat.Seat;


@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationCommandService implements ReservationCommandUseCase {

    private final SeatQueryPort seatQueryPort;
    private final ReservationQueryPort reservationQueryPort;
    private final ScreeningQueryPort screeningQueryPort;
    private final ReservationCommandPort reservationCommandPort;

    private final ReservationLockPort reservationLockPort;

    /*
    적용 비지니스 규칙
    1. 들어온 좌석은 연속된 좌석이어야 한다.
    2. 이미 예약이 존재하는 좌석은 예약이 불가능하다.
    3. 사용자의 예약은 모두 연속된 좌석이어야 한다.
    4. 사용자는 최대 해당 상영관에 대해서 5개까지 예약이 가능하다.
     */
    @Override
    public boolean reserve(ReserveCommandParam param) {
        List<String> seatIds = param.getSeatIds().stream().map(String::valueOf).toList();
        boolean lock = reservationLockPort.tryScreeningSeatLock(
                makeLockKey(param.getScreeningId().toString(), seatIds),
                20,
                1000);

        if (!lock) {
            log.info("locking screening for seat {} failed", param.getScreeningId());
            throw new DataInvalidException(ErrorCode.SEAT_ALREADY_RESERVED, param.getSeatIds().toString());
        }

        try {
            // 연속된 좌석인지 여부
            List<Seat> seats = seatQueryPort.getSeats(param.getSeatIds());
            if (!Seat.isSeries(seats)) {
                throw new DataInvalidException(ErrorCode.SEAT_REQUIRED_SERIES);
            }

            // 예약 가져오기
            List<Reservation> originReservations = reservationQueryPort.getReservations(param.getScreeningId());

            List<UUID> seatList = originReservations.stream()
                    .flatMap(reservation -> reservation.getSeats().stream())
                    .map(Seat::getSeatId)
                    .collect(Collectors.toList());

            // 이미 예약이 존재하는 좌석인지 검증
            boolean isAlreadyReservation = seatList.retainAll(param.getSeatIds());

            if( isAlreadyReservation ) {
                throw new DataInvalidException(ErrorCode.SEAT_ALREADY_RESERVED, seatList.toString());
            }

            List<Seat> originSeats = originReservations.stream()
                    .filter(reservation -> reservation.getUsername().equals(param.getUserName()))
                    .flatMap(reservation -> reservation.getSeats().stream())
                    .toList();

            // 이전 예약 + 현재 예약하려는 좌석의 연속성 검증 && 5개 이하의 예약 검증
            Seat.isAvailable(originSeats, seats);

            Screening screening = !CollectionUtils.isEmpty(originReservations)
                    ? originReservations.getFirst().getScreening()
                    : screeningQueryPort.getScreening(param.getScreeningId());

            if (!screening.isLaterScreening()) {
                throw new DataInvalidException(ErrorCode.SCREENING_REQUIRED_LATER_NOW, param.getScreeningId());
            }

            Reservation reservation
                    = Reservation.generateReservation(
                    null, LocalDateTime.now(), param.getUserName(), screening, seats);

            reservationCommandPort.reserve(reservation);
            return true;
        } finally {
            reservationLockPort.releaseMultiLock(makeLockKey(param.getScreeningId().toString(), seatIds));
        }
    }

    private List<String> makeLockKey(String screeningId, List<String> list) {
        return list.stream()
                .map(seatId -> "reservation-lock:" + screeningId + ":" + seatId)
                .toList();
    }
}