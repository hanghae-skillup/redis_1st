package project.redis.application.reservation.port.outbound;

import project.redis.domain.reservation.Reservation;

public interface ReservationCommandPort {
    void reserve(Reservation reservation);
}
