package project.redis.application.genre.port.outbound;

import java.util.List;
import project.redis.domain.genre.Genre;

public interface GenreQueryPort {

    List<Genre> findAllGenres();
}
