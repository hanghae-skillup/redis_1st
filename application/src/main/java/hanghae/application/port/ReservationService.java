package hanghae.application.port;

import hanghae.application.dto.request.ReservationRequest;
import hanghae.application.dto.response.ReservationResponse;

public interface ReservationService {

    ReservationResponse reserveSeat(ReservationRequest request);
}
