package hanghae.domain.port;

import hanghae.domain.domain.Showtime;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowtimeRepository {
    List<Showtime> findShowtimeByMovieId(Long movieId);
}