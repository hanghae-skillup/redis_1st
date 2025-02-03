package project.redis.infrastructure.screening.inbound.port.inbound;

import java.util.List;
import project.redis.domain.screening.Screening;

public interface ScreeningQueryPort {

    List<Screening> getScreenings(int maxScreeningDay);
}
