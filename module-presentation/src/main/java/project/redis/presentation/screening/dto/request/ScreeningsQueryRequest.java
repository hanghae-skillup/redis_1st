package project.redis.presentation.screening.dto.request;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScreeningsQueryRequest {
    @Builder.Default
    private int maxScreeningDay = 2;
    private String movieName;
    private String genreName;
}
