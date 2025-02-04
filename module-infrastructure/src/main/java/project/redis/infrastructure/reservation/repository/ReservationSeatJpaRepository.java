package project.redis.infrastructure.reservation.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import project.redis.infrastructure.reservation.entity.ReservationSeatJpaEntity;

public interface ReservationSeatJpaRepository extends JpaRepository<ReservationSeatJpaEntity, UUID> {
    @EntityGraph(attributePaths = {"seat", "seat.cinema"})
    List<ReservationSeatJpaEntity> findByScreeningId(UUID screeningId);
}
