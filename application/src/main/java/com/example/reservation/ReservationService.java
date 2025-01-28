package com.example.reservation;

import com.example.entity.reservation.Reservation;
import com.example.entity.reservation.ReservedSeat;
import com.example.message.MessageService;
import com.example.repository.reservation.ReservationRepository;
import com.example.reservation.request.ReservationServiceRequest;
import com.example.reservation.response.ReservationServiceResponse;
import com.example.reservation.validator.ReservationValidate;
import com.example.reservation.validator.ReservationValidationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final MessageService messageService;
    private final ReservationValidate reservationValidate;
    private final ReservationRepository reservationRepository;

    @Transactional
    public ReservationServiceResponse reserve(ReservationServiceRequest request) {

        ReservationValidationResult validationResult = reservationValidate.validate(request);

        Reservation reservation = createReservation(validationResult);

        reservationRepository.save(reservation);

        messageService.send();

        return ReservationServiceResponse.of(reservation);
    }

    private Reservation createReservation(ReservationValidationResult validationResult) {
        Reservation reservation = Reservation.builder()
                .member(validationResult.getMember())
                .screening(validationResult.getScreening())
                .build();

        reservation.reservation(validationResult.getSeats());

        List<ReservedSeat> reservedSeats = validationResult.getSeats().getSeats().stream()
                .map(seat -> new ReservedSeat(reservation, seat))
                .toList();

        reservation.addReservedSeat(reservedSeats);

        return reservation;
    }


}
