package hanghae.domain.port;

import hanghae.domain.entity.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    Reservation reserve(Reservation reservation);

    Optional<List<Reservation>> findReservationByScheduleId(Long scheduleId);

    Optional<List<Reservation>> findReservationByMemberIdAndScheduleId(Long memberId, Long scheduleId);
}
