package project.redis.application.cinema.port.outbound;

public interface CinemaCommandPort {

    void createCinema(String cinemaName) throws IllegalArgumentException;
}
