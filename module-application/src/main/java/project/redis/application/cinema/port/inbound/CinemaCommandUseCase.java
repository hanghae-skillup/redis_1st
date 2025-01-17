package project.redis.application.cinema.port.inbound;

public interface CinemaCommandUseCase {

    void createCinema(CinemaCreateCommandParam param);
}
