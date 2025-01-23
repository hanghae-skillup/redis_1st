package project.redis.domain.genre;

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
public class Genre {
    UUID genreId;
    String genreName;

    public static Genre generateGenre(UUID genreId, String genreName) {
        return new Genre(genreId, genreName);
    }
}
