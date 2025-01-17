package project.redis.presentation.screening.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupedScreeningResponse {

    private String movieId;
    private String movieTitle;
    private String rating;
    private LocalDate releaseDate;
    private String thumbnailUrl;
    private int runningMinTime;
    private String genreId;
    private String genreName;
    private List<ScreeningDetail> screenings;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScreeningDetail {
        private String screeningId;
        private LocalDateTime screeningStartTime;
        private LocalDateTime screeningEndTime;
        private String cinemaId;
        private String cinemaName;
    }
}
