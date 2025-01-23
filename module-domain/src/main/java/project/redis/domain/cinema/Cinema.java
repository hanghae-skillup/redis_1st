package project.redis.domain.cinema;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@Getter
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true)
public class Cinema {
    UUID cinemaId;
    String cinemaName;

    public static Cinema generateCinema(UUID cinemaId, String cinemaName) {
        return new Cinema(cinemaId, cinemaName);
    }
}