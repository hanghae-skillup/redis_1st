package project.redis.infrastructure.screening.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.redis.common.exception.DataInvalidException;
import project.redis.common.exception.ErrorCode;
import project.redis.infrastructure.screening.entity.ScreeningJpaEntity;

public interface ScreeningJpaRepository extends JpaRepository<ScreeningJpaEntity, UUID>, ScreeningJpaRepositoryCustom {

    @Query("select s from ScreeningJpaEntity s "
            + "left join fetch s.movie m "
            + "left join fetch s.movie.genre g "
            + "left join fetch s.cinema c "
            + "where date(s.screeningStartTime) BETWEEN current_date AND :limit "
            + "order by s.screeningStartTime asc, m.releaseDate desc")
    List<ScreeningJpaEntity> findAllOrderByReleaseDescAndScreenStartTimeAsc(@Param("limit") LocalDate limit);

    @EntityGraph(attributePaths = {"movie", "cinema", "movie.genre"})
    Optional<ScreeningJpaEntity> findOneById(UUID screeningId);

    default ScreeningJpaEntity findByIdOrThrow(UUID id) {
        return findOneById(id).orElseThrow(() -> new DataInvalidException(ErrorCode.NOT_FOUND, id));
    }
}
