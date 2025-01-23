package project.redis.application.cinema.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.redis.application.cinema.port.inbound.CinemaQueryUseCase;
import project.redis.application.cinema.port.outbound.CinemaQueryPort;
import project.redis.domain.cinema.Cinema;


@Service
@RequiredArgsConstructor
public class CinemaQueryService implements CinemaQueryUseCase {

    private final CinemaQueryPort cinemaQueryPort;

    @Override
    public List<Cinema> getCinemas() {
        return cinemaQueryPort.getCinemas();
    }
}
