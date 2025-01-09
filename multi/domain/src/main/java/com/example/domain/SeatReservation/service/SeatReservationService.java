package com.example.domain.SeatReservation.service;

import com.example.domain.SeatReservation.SeatReservation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SeatReservationService {
    private final SeatReservationRepository seatReservationRepository;

    public SeatReservation getSeatReservationInfo(Long seatReservationId) {
        return seatReservationRepository.getSeatReservationInfo(seatReservationId);
    }
}
