package hanghae.infrastructure.repository;

import hanghae.infrastructure.domain.entity.TheaterShowtimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTheaterShowtimeRepository extends JpaRepository<TheaterShowtimeEntity, Long> {
}
