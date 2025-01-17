package project.redis.infrastructure.screening.inbound;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.redis.domain.screening.Screening;
import project.redis.infrastructure.screening.entity.ScreeningJpaEntity;
import project.redis.infrastructure.screening.inbound.port.inbound.ScreeningQueryPort;
import project.redis.infrastructure.screening.mapper.ScreeningInfraMapper;
import project.redis.infrastructure.screening.repository.ScreeningJpaRepository;


@Transactional(readOnly = true)
@Component
@RequiredArgsConstructor
public class ScreeningQueryAdapter implements ScreeningQueryPort {

    private final ScreeningJpaRepository screeningJpaRepository;

    @Override
    public List<Screening> getScreenings(int maxScreeningDay) {

        LocalDate maxScreeningDate = LocalDate.now().plusDays(maxScreeningDay);

        List<ScreeningJpaEntity> screenings
                = screeningJpaRepository.findAllOrderByReleaseDescAndScreenStartTimeAsc(maxScreeningDate);

        return screenings.stream()
                .map(ScreeningInfraMapper::toScreening)
                .toList();
    }
}
