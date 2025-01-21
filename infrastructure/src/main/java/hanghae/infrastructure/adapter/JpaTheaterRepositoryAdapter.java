package hanghae.infrastructure.adapter;

import hanghae.domain.domain.Movie;
import hanghae.domain.domain.Theater;
import hanghae.domain.port.TheaterRepository;
import hanghae.infrastructure.domain.entity.TheaterEntity;
import hanghae.infrastructure.repository.JpaTheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaTheaterRepositoryAdapter implements TheaterRepository {

    private final JpaTheaterRepository jpaTheaterRepository;

    @Override
    public Optional<List<Theater>> findTheaterByMovie(Movie movie) {
        return jpaTheaterRepository.findTheaterEntitiesByMovieId(movie.getMovieId())
                .map(this::toTheater);
    }

    private List<Theater> toTheater(List<TheaterEntity> theaterEntities) {
        return theaterEntities.stream()
                .map(TheaterEntity::toTheaterDomain)
                .toList();
    }
}
