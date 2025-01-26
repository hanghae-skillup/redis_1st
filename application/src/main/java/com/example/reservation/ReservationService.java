package com.example.reservation;

import com.example.entity.reservation.Reservation;
import com.example.repository.reservation.ReservationRepository;
import com.example.reservation.request.ReservationServiceRequest;
import com.example.reservation.response.ReservationServiceResponse;
import com.example.reservation.validator.ReservationValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationValidate reservationValidate;
    private final ReservationRepository reservationRepository;

    @Transactional
    public ReservationServiceResponse reserve(ReservationServiceRequest request) {

        Reservation reservation = reservationValidate.validate(request);

        reservationRepository.save(reservation);

        return ReservationServiceResponse.of(reservation);
    }

}
