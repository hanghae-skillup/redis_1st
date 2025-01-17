package project.redis.infrastructure.cinema.inbound.port;

import java.util.List;
import project.redis.domain.cinema.Cinema;

public interface CinemaQueryPort {

    List<Cinema> getCinemas();
}
