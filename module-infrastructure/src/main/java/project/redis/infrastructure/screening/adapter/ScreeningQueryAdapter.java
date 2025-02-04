package project.redis.infrastructure.screening.adapter;

import static project.redis.infrastructure.common.config.RedisConfig.REDIS_SCREENING;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.redis.application.screening.port.outbound.ScreeningQueryFilter;
import project.redis.application.screening.port.outbound.ScreeningQueryPort;
import project.redis.domain.screening.Screening;
import project.redis.infrastructure.screening.entity.ScreeningJpaEntity;
import project.redis.infrastructure.screening.mapper.ScreeningInfraMapper;
import project.redis.infrastructure.screening.repository.ScreeningJpaRepository;


@Transactional(readOnly = true)
@Component
@RequiredArgsConstructor
public class ScreeningQueryAdapter implements ScreeningQueryPort {

    private final ScreeningJpaRepository screeningJpaRepository;

    @Override
    public List<Screening> getScreenings(ScreeningQueryFilter filter) {

        LocalDate maxScreeningDate = LocalDate.now().plusDays(filter.getMaxScreeningDay());

        List<ScreeningJpaEntity> screeningsByFilter = screeningJpaRepository.findScreeningsByFilter(
                maxScreeningDate,
                filter.getGenreName(),
                filter.getMovieName()
        );

        return screeningsByFilter.stream()
                .map(ScreeningInfraMapper::toScreening)
                .toList();
    }

    @Override
    @Cacheable(value = REDIS_SCREENING, cacheManager = "redisCacheManager", keyGenerator = "screeningKeyGenerator")
    public List<Screening> getScreeningsRedis(ScreeningQueryFilter filter) {
        LocalDate maxScreeningDate = LocalDate.now().plusDays(filter.getMaxScreeningDay());

        List<ScreeningJpaEntity> screeningsByFilter = screeningJpaRepository.findScreeningsByFilter(
                maxScreeningDate,
                filter.getGenreName(),
                filter.getMovieName()
        );

        return screeningsByFilter.stream()
                .map(ScreeningInfraMapper::toScreening)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = REDIS_SCREENING, cacheManager = "localCacheManager", keyGenerator = "screeningKeyGenerator")
    public List<Screening> getScreeningsLocalCache(ScreeningQueryFilter filter) {
        LocalDate maxScreeningDate = LocalDate.now().plusDays(filter.getMaxScreeningDay());

        List<ScreeningJpaEntity> screeningsByFilter = screeningJpaRepository.findScreeningsByFilter(
                maxScreeningDate,
                filter.getGenreName(),
                filter.getMovieName()
        );

        return screeningsByFilter.stream()
                .map(ScreeningInfraMapper::toScreening)
                .toList();
    }

    @Override
    public Screening getScreening(UUID screeningId) {
        ScreeningJpaEntity screeningEntity = screeningJpaRepository.findByIdOrThrow(screeningId);
        return ScreeningInfraMapper.toScreening(screeningEntity);
    }
}
