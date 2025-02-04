package project.redis.infrastructure.reservation.mapper;

import project.redis.domain.reservation.Reservation;
import project.redis.infrastructure.reservation.entity.ReservationJpaEntity;
import project.redis.infrastructure.screening.mapper.ScreeningInfraMapper;

public class ReservationInfraMapper {

    public static ReservationJpaEntity toEntity(Reservation reservation) {
        return ReservationJpaEntity.builder()
                .id(reservation.getReservationId())
                .reservationTime(reservation.getReservationTime())
                .username(reservation.getUsername())
                .screening(ScreeningInfraMapper.toEntity(reservation.getScreening()))
                .build();
    }

}
