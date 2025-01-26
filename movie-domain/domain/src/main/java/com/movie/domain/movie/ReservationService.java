package com.movie.domain.movie;

import com.movie.domain.movie.domain.Reservation;
import com.movie.domain.movie.dto.command.ReservationCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public void makeReservation(ReservationCommand.Reserve reserve) {
        // in을 이요하여 한번에 디비에서 예약을 하는게 맞는지, 아님 foreach로 하는게 맞는지

        reservationRepository.makeReservation(reserve);

        // 예약이 완료되면 message 전송
    }

    public List<Reservation> getReservations(ReservationCommand.Get reservationData) {
        return reservationRepository.getReservations(reservationData);
    }

}
