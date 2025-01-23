package project.redis.application.genre.port.inbound;

import java.util.List;
import project.redis.domain.genre.Genre;

public interface GenreQueryUseCase {

    List<Genre> getGenres();
}
