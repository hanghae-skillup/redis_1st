package hanghae.infrastructure.repository;

import hanghae.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<List<Reservation>> findReservationsByScheduleScheduleId(Long scheduleId);

    Optional<List<Reservation>> findReservationByMemberMemberIdAndScheduleScheduleId(Long memberId, Long scheduleId);
}
