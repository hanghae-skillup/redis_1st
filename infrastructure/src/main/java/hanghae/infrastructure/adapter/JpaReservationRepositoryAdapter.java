package hanghae.infrastructure.adapter;

import hanghae.domain.entity.Reservation;
import hanghae.domain.port.ReservationRepository;
import hanghae.infrastructure.repository.JpaReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaReservationRepositoryAdapter implements ReservationRepository {

    private final JpaReservationRepository jpaReservationRepository;

    @Override
    public Reservation reserve(Reservation reservation) {
        return jpaReservationRepository.save(reservation);
    }

    @Override
    public Optional<List<Reservation>> findReservationByScheduleId(Long scheduleId) {
        return jpaReservationRepository.findReservationsByScheduleScheduleId(scheduleId);
    }

    @Override
    public Optional<List<Reservation>> findReservationByMemberIdAndScheduleId(Long memberId, Long scheduleId) {
        return jpaReservationRepository.findReservationByMemberMemberIdAndScheduleScheduleId(memberId, scheduleId);
    }
}
