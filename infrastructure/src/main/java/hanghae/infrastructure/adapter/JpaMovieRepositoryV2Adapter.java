package hanghae.infrastructure.adapter;

import hanghae.domain.domain.Movie;
import hanghae.domain.port.MovieRepository;
import hanghae.infrastructure.domain.entity.MovieEntity;
import hanghae.infrastructure.repository.JpaMovieRepositoryV2;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Primary
@Repository
@RequiredArgsConstructor
public class JpaMovieRepositoryV2Adapter implements MovieRepository {

    private final JpaMovieRepositoryV2 jpaMovieRepository;

    @Override
    public Optional<List<Movie>> findMoviesPlaying(LocalDateTime now) {
        return jpaMovieRepository.findMoviesPlayingV2(now)
                .map(this::toMovieList);
    }

    private List<Movie> toMovieList(List<MovieEntity> movieEntities) {
        return movieEntities.stream()
                .map(MovieEntity::toMovieDomain)
                .toList();
    }
}
