package hanghae.application.dto.request;

import java.util.List;

public record ReservationRequest(
        Long memberId,
        Long screenId,
        Long scheduleId,
        List<String> seatNames
) {}
