package project.redis.infrastructure.cinema.mapper;


import project.redis.domain.cinema.Cinema;
import project.redis.infrastructure.cinema.entity.CinemaJpaEntity;

public class CinemaInfraMapper {

    public static Cinema toCinema(CinemaJpaEntity cinema) {
        return Cinema.generateCinema(
                cinema.getId(),
                cinema.getCinemaName()
        );
    }

}
