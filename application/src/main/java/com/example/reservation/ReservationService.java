package com.example.reservation;

import com.example.entity.reservation.Reservation;
import com.example.entity.reservation.ReservedSeat;
import com.example.func.DistributedLockExecutor;
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
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final MessageService messageService;
    private final ReservationValidate reservationValidate;
    private final ReservationRepository reservationRepository;
    private final DistributedLockExecutor lockExecutor;

//    @DistributedLock(key = "reservation:screening:{#request.screeningId}:seat:{#request.seatIds}", leaseTime = 2, waitTime = 2)
    @Transactional
    public ReservationServiceResponse reserve(ReservationServiceRequest request) {

        List<String> lockKeys = request.getSeatIds().stream()
                .map(seatId -> "reservation:screening:" + request.getScreeningId() + ":seat:" + seatId)
                .toList();

        return lockExecutor.executeWithLock(lockKeys, 2, 2, TimeUnit.SECONDS, () -> {
            ReservationValidationResult validationResult = reservationValidate.validate(request);

            Reservation reservation = createReservation(validationResult);

            reservationRepository.save(reservation);

            messageService.send();

            return ReservationServiceResponse.of(reservation);
        });
    }

    private Reservation createReservation(ReservationValidationResult validationResult) {
        Reservation reservation = Reservation.builder()
                .member(validationResult.getMember())
                .screening(validationResult.getScreening())
                .build();

        List<ReservedSeat> reservedSeats = validationResult.getSeats().getSeats().stream()
                .map(seat -> new ReservedSeat(reservation, seat))
                .toList();

        reservation.addReservedSeat(reservedSeats);

        return reservation;
    }
}
