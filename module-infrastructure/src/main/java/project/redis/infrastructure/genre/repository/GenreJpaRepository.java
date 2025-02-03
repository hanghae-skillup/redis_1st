package project.redis.infrastructure.genre.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import project.redis.infrastructure.genre.entity.GenreJpaEntity;

public interface GenreJpaRepository extends JpaRepository<GenreJpaEntity, UUID> {
}
