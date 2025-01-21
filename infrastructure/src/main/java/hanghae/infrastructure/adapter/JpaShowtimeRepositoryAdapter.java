package hanghae.infrastructure.adapter;

import hanghae.domain.domain.Movie;
import hanghae.domain.domain.Showtime;
import hanghae.domain.domain.Theater;
import hanghae.domain.port.ShowtimeRepository;
import hanghae.infrastructure.domain.entity.ShowtimeEntity;
import hanghae.infrastructure.domain.entity.TheaterEntity;
import hanghae.infrastructure.repository.JpaShowtimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaShowtimeRepositoryAdapter implements ShowtimeRepository {

    private final JpaShowtimeRepository jpaShowtimeRepository;

    @Override
    public List<Showtime> findShowtimeByMovie(Movie movie) {
        return jpaShowtimeRepository.findShowtimeEntitiesByMovie_MovieIdOrderBySchedule(movie.getMovieId())
                .stream()
                .map(showtimeEntity -> {
                    List<Theater> theaters = findTheaterByShowtime(showtimeEntity);
                    return toShowtime(showtimeEntity, movie, theaters);
                })
                .toList();
    }

    private Showtime toShowtime(ShowtimeEntity showtimeEntity, Movie movie, List<Theater> theaters) {
        return ShowtimeEntity.showtimeDomainOf(showtimeEntity, movie, theaters);
    }

    private List<Theater> findTheaterByShowtime(ShowtimeEntity showtimeEntity) {
        List<TheaterEntity> theaterEntities =
                jpaShowtimeRepository.findTheaterEntitiesByShowtimeId(showtimeEntity.getShowtimeId());

        return theaterEntities.stream()
                .map(TheaterEntity::toTheaterDomain)
                .toList();
    }
}
