package project.redis.infrastructure.cinema.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import project.redis.infrastructure.cinema.entity.CinemaJpaEntity;

public interface CinemaJpaRepository extends JpaRepository<CinemaJpaEntity, UUID> {

    Optional<CinemaJpaEntity> findByCinemaName(String cinemaName);
}
