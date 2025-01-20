package hanghae.infrastructure.repository;

import hanghae.infrastructure.domain.entity.ShowtimeEntity;
import hanghae.infrastructure.domain.entity.TheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaShowtimeRepository extends JpaRepository<ShowtimeEntity, Long> {
    List<ShowtimeEntity> findShowtimeEntitiesByMovie_MovieIdOrderBySchedule(Long movieId);

    @Query("SELECT ts.theater FROM TheaterShowtimeEntity ts WHERE ts.showtime.showtimeId = :showtimeId")
    List<TheaterEntity> findTheaterEntitiesByShowtimeId(@Param("showtimeId") Long showtimeId);
}
