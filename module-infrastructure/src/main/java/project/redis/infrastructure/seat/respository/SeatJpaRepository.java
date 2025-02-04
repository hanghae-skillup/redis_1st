package project.redis.infrastructure.seat.respository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import project.redis.infrastructure.seat.entity.SeatJpaEntity;

public interface SeatJpaRepository extends JpaRepository<SeatJpaEntity, UUID> {

    List<SeatJpaEntity> findByIdIn(Collection<UUID> ids);

    @EntityGraph(attributePaths = {"cinema"})
    List<SeatJpaEntity> findByCinemaId(UUID cinemaId);
}
