package project.redis.application.genre.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.redis.application.genre.port.inbound.GenreQueryUseCase;
import project.redis.application.genre.port.outbound.GenreQueryPort;
import project.redis.domain.genre.Genre;

@Service
@RequiredArgsConstructor
public class GenreQueryService implements GenreQueryUseCase {

    private final GenreQueryPort genreQueryPort;

    @Override
    public List<Genre> getGenres() {
        return List.of();
    }
}
