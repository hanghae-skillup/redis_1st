package project.redis.infrastructure.seat.mapper;

import project.redis.domain.cinema.Cinema;
import project.redis.domain.seat.Seat;
import project.redis.infrastructure.cinema.mapper.CinemaInfraMapper;
import project.redis.infrastructure.seat.entity.SeatJpaEntity;

public class SeatInfraMapper {

    public static Seat toSeatOnly(SeatJpaEntity seatJpaEntity) {
        return Seat.generateSeat(
                seatJpaEntity.getId(),
                seatJpaEntity.getSeatNumber(),
                Cinema.generateCinema(seatJpaEntity.getCinema().getId(), null)
        );
    }

    public static Seat toSeat(SeatJpaEntity seatJpaEntity) {
        return Seat.generateSeat(
                seatJpaEntity.getId(),
                seatJpaEntity.getSeatNumber(),
                Cinema.generateCinema(
                        seatJpaEntity.getCinema().getId(),
                        seatJpaEntity.getCinema().getCinemaName()
                )
        );
    }

    public static SeatJpaEntity toEntity(Seat seat) {
        return SeatJpaEntity.builder()
                .id(seat.getSeatId())
                .cinema(CinemaInfraMapper.toEntity(seat.getCinema()))
                .seatNumber(seat.getSeatNumber())
                .build();
    }
}
