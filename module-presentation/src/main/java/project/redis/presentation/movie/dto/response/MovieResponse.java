package project.redis.presentation.movie.dto.response;


import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponse {

    private UUID movieId;
    private String title;
    private String rating;
    private LocalDate releaseDate;
    private String thumbnailUrl;
    private int runningTimeMin;
    private String genreName;
}
