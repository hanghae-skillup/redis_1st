package project.redis.application.cinema.port.inbound;

import java.util.List;
import project.redis.domain.cinema.Cinema;

public interface CinemaQueryUseCase {

    List<Cinema> getCinemas();
}
