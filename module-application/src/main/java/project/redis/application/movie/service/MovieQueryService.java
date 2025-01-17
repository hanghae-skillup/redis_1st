package project.redis.application.movie.service;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.redis.application.movie.port.inbound.MovieQueryUseCase;
import project.redis.domain.movie.Movie;
import project.redis.infrastructure.movie.inbound.port.MovieQueryPort;

@Service
@RequiredArgsConstructor
public class MovieQueryService implements MovieQueryUseCase {

    private final MovieQueryPort movieQueryPort;

    @Override
    public List<Movie> getMovies() {
        return movieQueryPort.getMovies();
    }
}
