package com.movie.domain.movie;

import com.movie.domain.movie.domain.Reservation;
import com.movie.domain.movie.domain.Seat;
import com.movie.domain.movie.dto.command.ReservationCommand;
import com.movie.domain.movie.message.ReservationMessage;
import com.movie.domain.movie.message.ReservationMessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMessagePublisher reservationMessagePublisher;
    private final ReservationRedisRepository reservationRedisRepository;

    public List<Reservation> makeReservation(ReservationCommand.Reserve reserve) {
        List<Reservation> reservations = reservationRepository.makeReservation(reserve);

        // 예약이 완료되면 message 전송
        ReservationMessage.Send send = ReservationMessage.Send.of("예약 완료");
        reservationMessagePublisher.sendMessage(send);

        return reservations;
    }

    public List<Reservation> getReservations(ReservationCommand.Get reservationData) {
        return reservationRepository.getReservations(reservationData);
    }

    @Transactional
    public void reserveSeats(Long scheduleId, List<Long> seatIds, List<Seat> seats, Long userId) {
        ReservationCommand.Get get = ReservationCommand.Get.of(scheduleId, seatIds);
        List<Reservation> reservations = getReservations(get);
        Reservation.isAlreadyReserved(reservations, seats);

        ReservationCommand.Reserve reserve = ReservationCommand.Reserve.of(scheduleId, seatIds, userId);
        List<Reservation> savedReservations = makeReservation(reserve);

        /** 예약 정보 캐시처리 - reservedAt value로 저장 */
        savedReservations.forEach(reservation -> {
            ReservationCommand.CreateCache createCache
                    = ReservationCommand.CreateCache.of(reservation.getScheduleId(), reservation.getUserId(), reservation.getReservedAt());
            reservationRedisRepository.saveReservationDone(createCache);
        });
    }
}
