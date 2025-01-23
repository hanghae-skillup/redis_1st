package project.redis.application.cinema.port.outbound;

import java.util.List;
import project.redis.domain.cinema.Cinema;

public interface CinemaQueryPort {

    List<Cinema> getCinemas();
}
