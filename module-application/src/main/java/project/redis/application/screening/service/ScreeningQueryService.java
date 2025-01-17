package project.redis.application.screening.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.redis.application.screening.port.inbound.ScreeningQueryUseCase;
import project.redis.application.screening.port.inbound.ScreeningsQueryParam;
import project.redis.domain.screening.Screening;
import project.redis.infrastructure.screening.inbound.port.inbound.ScreeningQueryPort;



@Service
@RequiredArgsConstructor
public class ScreeningQueryService implements ScreeningQueryUseCase {

    private final ScreeningQueryPort screeningQueryPort;

    @Override
    public List<Screening> getScreenings(ScreeningsQueryParam param) {
        return screeningQueryPort.getScreenings(param.getMaxScreeningDay());
    }
}
