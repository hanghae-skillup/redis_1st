package hanghae.infrastructure.repository;

import hanghae.infrastructure.domain.entity.TheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaTheaterRepository extends JpaRepository<TheaterEntity, Long> {
    @Query("SELECT mt.theater FROM MovieTheaterEntity mt WHERE mt.movie.movieId = :movieId")
    Optional<List<TheaterEntity>> findTheaterEntitiesByMovieId(@Param("movieId") Long movieId);
}
