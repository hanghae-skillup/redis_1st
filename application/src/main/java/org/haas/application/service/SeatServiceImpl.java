package org.haas.application.service;

import lombok.RequiredArgsConstructor;
import org.haas.core.domain.seat.repository.SeatRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;


    public boolean reserveSeat(int row, int column) {
//        SeatEntity seatEntity = seatRepository.findByRowAndColumn(row, column)
//                .orElseThrow( () -> new AlreadyReservedException("이미 예약된 좌석입니다"));
        return false;
    }

    public boolean reservationAvailable(int row, int column) {
//        return seatRepository.findByRowAndColumn(row, column)
//                .map(SeatEntity::availableReservation)
//                .orElse(false);
        return false;
    }

}
