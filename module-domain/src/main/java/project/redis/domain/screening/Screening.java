package project.redis.domain.screening;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import project.redis.domain.cinema.Cinema;
import project.redis.domain.movie.Movie;

@Getter
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Screening {
    UUID screeningId;
    LocalDateTime screenStartTime;
    LocalDateTime screenEndTime;
    Movie movie;
    Cinema cinema;

    public static Screening generateScreening(
            UUID screeningId,
            LocalDateTime screenStartTime, LocalDateTime screenEndTime,
            Movie movie, Cinema cinema) {
        assert screeningId != null;
        assert screenStartTime != null;
        assert screenEndTime != null;
        assert movie != null;
        assert cinema != null;

        return new Screening(screeningId, screenStartTime, screenEndTime, movie, cinema);
    }
}
