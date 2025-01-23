package project.redis.presentation.cinema.dto.response;


import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CinemaResponse {
    private UUID cinemaId;
    private String cinemaName;
}
