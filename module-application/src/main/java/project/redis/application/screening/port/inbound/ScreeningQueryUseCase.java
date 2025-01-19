package project.redis.application.screening.port.inbound;

import java.util.List;
import project.redis.domain.screening.Screening;

public interface ScreeningQueryUseCase {

    List<Screening> getScreenings(ScreeningsQueryParam param);

//    List<Screening> getScreeningsLocalCache(ScreeningsQueryParam param);

    List<Screening> getScreeningsRedis(ScreeningsQueryParam param);

}
