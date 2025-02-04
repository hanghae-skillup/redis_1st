package project.redis.application.reservation.port.outbound;

import java.util.List;
import java.util.UUID;
import project.redis.domain.reservation.Reservation;

public interface ReservationQueryPort {

    List<Reservation> getReservations(UUID screeningId);
}
