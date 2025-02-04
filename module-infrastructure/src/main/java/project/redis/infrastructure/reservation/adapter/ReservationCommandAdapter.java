package project.redis.infrastructure.reservation.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.redis.application.reservation.port.outbound.ReservationCommandPort;
import project.redis.domain.reservation.Reservation;
import project.redis.domain.seat.Seat;
import project.redis.infrastructure.reservation.entity.ReservationJpaEntity;
import project.redis.infrastructure.reservation.entity.ReservationSeatJpaEntity;
import project.redis.infrastructure.reservation.mapper.ReservationInfraMapper;
import project.redis.infrastructure.reservation.repository.ReservationJpaRepository;
import project.redis.infrastructure.reservation.repository.ReservationSeatJpaRepository;
import project.redis.infrastructure.screening.mapper.ScreeningInfraMapper;
import project.redis.infrastructure.seat.mapper.SeatInfraMapper;


@Transactional
@Component
@RequiredArgsConstructor
public class ReservationCommandAdapter implements ReservationCommandPort {

    private final ReservationJpaRepository reservationJpaRepository;
    private final ReservationSeatJpaRepository reservationSeatJpaRepository;

    @Override
    public void reserve(Reservation reservation) {
        ReservationJpaEntity savedReservation = reservationJpaRepository.save(ReservationInfraMapper.toEntity(reservation));

        for (Seat seat : reservation.getSeats()) {
            ReservationSeatJpaEntity reservationSeat = ReservationSeatJpaEntity.builder()
                    .reservation(savedReservation)
                    .screening(ScreeningInfraMapper.toEntity(reservation.getScreening()))
                    .seat(SeatInfraMapper.toEntity(seat))
                    .build();

            reservationSeatJpaRepository.save(reservationSeat);
        }
    }
}
