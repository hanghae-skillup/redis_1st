package org.haas.infrastructure.persistence.reservation.repository;

import lombok.RequiredArgsConstructor;
import org.haas.core.domain.reservation.repository.ReservationRepository;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationJpaRepository reservationJpaRepository;

}
