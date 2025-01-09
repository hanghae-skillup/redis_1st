package com.example.infrastructure.seatreservation;

import com.example.domain.SeatReservation.SeatReservation;
import com.example.domain.SeatReservation.service.SeatReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SeatReservationRepositoryImpl implements SeatReservationRepository {

    private final SeatReservationJpaRepository seatReservationJpaRepository;

    @Override
    public SeatReservation getSeatReservationInfo(Long seatReservationId) {
        return seatReservationJpaRepository.findById(seatReservationId).orElse(null);
    }
}
