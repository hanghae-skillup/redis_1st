package project.redis.presentation.reservation.dto.request;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationCommandRequest {
    private String username;
    private UUID screeningId;
    private List<UUID> seatIds;
}
