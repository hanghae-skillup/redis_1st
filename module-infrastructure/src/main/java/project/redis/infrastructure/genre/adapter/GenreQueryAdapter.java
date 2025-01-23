package project.redis.infrastructure.genre.adapter;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.redis.application.genre.port.outbound.GenreQueryPort;
import project.redis.domain.genre.Genre;
import project.redis.infrastructure.genre.mapper.GenreInfraMapper;
import project.redis.infrastructure.genre.repository.GenreJpaRepository;


@Component
@RequiredArgsConstructor
public class GenreQueryAdapter implements GenreQueryPort {

    private final GenreJpaRepository genreJpaRepository;

    @Override
    public List<Genre> findAllGenres() {
        return genreJpaRepository.findAll().stream()
                .map(GenreInfraMapper::toGenre)
                .toList();
    }
}
