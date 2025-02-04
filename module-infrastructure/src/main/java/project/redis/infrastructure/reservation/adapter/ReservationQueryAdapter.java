package project.redis.infrastructure.reservation.adapter;


import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import project.redis.application.reservation.port.outbound.ReservationQueryPort;
import project.redis.domain.reservation.Reservation;
import project.redis.domain.seat.Seat;
import project.redis.infrastructure.reservation.entity.ReservationJpaEntity;
import project.redis.infrastructure.reservation.entity.ReservationSeatJpaEntity;
import project.redis.infrastructure.reservation.repository.ReservationJpaRepository;
import project.redis.infrastructure.reservation.repository.ReservationSeatJpaRepository;
import project.redis.infrastructure.screening.entity.ScreeningJpaEntity;
import project.redis.infrastructure.screening.mapper.ScreeningInfraMapper;
import project.redis.infrastructure.seat.mapper.SeatInfraMapper;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationQueryAdapter implements ReservationQueryPort {

    private final ReservationJpaRepository reservationJpaRepository;
    private final ReservationSeatJpaRepository reservationSeatJpaRepository;

    @Override
    public List<Reservation> getReservations(UUID screeningId) {
        List<ReservationJpaEntity> reservations = reservationJpaRepository.findAllByScreeningId(screeningId);

        if (CollectionUtils.isEmpty(reservations)) {
            return List.of();
        }

        List<ReservationSeatJpaEntity> reservationSeats = reservationSeatJpaRepository.findByScreeningId(screeningId);

        Map<UUID, List<ReservationSeatJpaEntity>> reservationIdToEntityMap = reservationSeats.stream()
                .collect(Collectors.groupingBy(
                        reservationSeatJpaEntity -> reservationSeatJpaEntity.getReservation().getId()));

        return reservations.stream()
                .map(reservation -> {
                    List<Seat> seats = reservationIdToEntityMap.get(reservation.getId()).stream()
                            .map(ReservationSeatJpaEntity::getSeat)
                            .map(SeatInfraMapper::toSeat)
                            .toList();

                    ScreeningJpaEntity screening = reservation.getScreening();

                    return Reservation.generateReservation(
                            reservation.getId(),
                            reservation.getReservationTime(),
                            reservation.getUsername(),
                            ScreeningInfraMapper.toScreening(screening),
                            seats
                    );
                })
                .toList();
    }
}
