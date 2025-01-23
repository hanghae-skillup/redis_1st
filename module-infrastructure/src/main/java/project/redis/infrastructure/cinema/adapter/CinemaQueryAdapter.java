package project.redis.infrastructure.cinema.adapter;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.redis.application.cinema.port.outbound.CinemaQueryPort;
import project.redis.domain.cinema.Cinema;
import project.redis.infrastructure.cinema.entity.CinemaJpaEntity;
import project.redis.infrastructure.cinema.mapper.CinemaInfraMapper;
import project.redis.infrastructure.cinema.repository.CinemaJpaRepository;


@Component
@RequiredArgsConstructor
public class CinemaQueryAdapter implements CinemaQueryPort {

    private final CinemaJpaRepository cinemaJpaRepository;

    @Override
    public List<Cinema> getCinemas() {
        List<CinemaJpaEntity> cinemas = cinemaJpaRepository.findAll();
        return cinemas.stream()
                .map(CinemaInfraMapper::toCinema)
                .toList();
    }
}
