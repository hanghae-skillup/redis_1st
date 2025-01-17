package project.redis.infrastructure.cinema.inbound.port;

public interface CinemaCommandPort {

    void createCinema(String cinemaName) throws IllegalArgumentException;
}
