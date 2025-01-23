package project.redis.infrastructure.screening.repository;

import java.time.LocalDate;
import java.util.List;
import project.redis.infrastructure.screening.entity.ScreeningJpaEntity;

public interface ScreeningJpaRepositoryCustom {

    List<ScreeningJpaEntity> findScreeningsByFilter(
            LocalDate maxScreeningDate,
            String genreName,
            String movieName
    );

}

