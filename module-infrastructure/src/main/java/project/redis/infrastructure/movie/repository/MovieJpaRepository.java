package project.redis.infrastructure.movie.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import project.redis.infrastructure.movie.entity.MovieJpaEntity;

public interface MovieJpaRepository extends JpaRepository<MovieJpaEntity, UUID> {

    @EntityGraph(attributePaths = {"genre"})
    List<MovieJpaEntity> findAll();
}
