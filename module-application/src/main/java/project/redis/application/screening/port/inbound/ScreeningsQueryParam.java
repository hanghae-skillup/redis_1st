package project.redis.application.screening.port.inbound;

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
public class ScreeningsQueryParam {
    private int maxScreeningDay;
    private String movieName;
    private String genreName;
}
