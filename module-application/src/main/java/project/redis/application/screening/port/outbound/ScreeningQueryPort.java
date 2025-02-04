package project.redis.application.screening.port.outbound;

import java.util.List;
import java.util.UUID;
import project.redis.domain.screening.Screening;

public interface ScreeningQueryPort {

    List<Screening> getScreenings(ScreeningQueryFilter filter);

    List<Screening> getScreeningsRedis(ScreeningQueryFilter filter);

    List<Screening> getScreeningsLocalCache(ScreeningQueryFilter filter);

    Screening getScreening(UUID screeningId);
}
