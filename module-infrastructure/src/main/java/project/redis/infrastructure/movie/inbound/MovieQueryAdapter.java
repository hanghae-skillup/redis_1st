package project.redis.infrastructure.movie.inbound;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.redis.domain.movie.Movie;
import project.redis.infrastructure.movie.entity.MovieJpaEntity;
import project.redis.infrastructure.movie.inbound.port.MovieQueryPort;
import project.redis.infrastructure.movie.mapper.MovieInfraMapper;
import project.redis.infrastructure.movie.repository.MovieJpaRepository;

@Component
@RequiredArgsConstructor
public class MovieQueryAdapter implements MovieQueryPort {

    private final MovieJpaRepository movieJpaRepository;

    @Override
    public List<Movie> getMovies() {
        List<MovieJpaEntity> movies = movieJpaRepository.findAll();

        return movies.stream()
                .map(MovieInfraMapper::toMovie)
                .toList();
    }
}
