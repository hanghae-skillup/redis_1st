package project.redis.application.cinema.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.redis.application.cinema.port.inbound.CinemaCommandUseCase;
import project.redis.application.cinema.port.inbound.CinemaCreateCommandParam;
import project.redis.infrastructure.cinema.inbound.port.CinemaCommandPort;


@Service
@RequiredArgsConstructor
public class CinemaCommandService implements CinemaCommandUseCase {

    private final CinemaCommandPort cinemaCommandPort;

    @Override
    public void createCinema(CinemaCreateCommandParam param) {
        cinemaCommandPort.createCinema(param.getCinemaName());
    }
}
