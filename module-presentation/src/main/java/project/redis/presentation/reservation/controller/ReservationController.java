package project.redis.presentation.reservation.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.redis.application.reservation.port.inbound.ReservationCommandUseCase;
import project.redis.application.reservation.port.inbound.ReserveCommandParam;
import project.redis.presentation.reservation.dto.request.ReservationCommandRequest;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationCommandUseCase reservationCommandUseCase;

    @PostMapping
    public ResponseEntity<Void> createReservation(@RequestBody ReservationCommandRequest request) {

        ReserveCommandParam param = new ReserveCommandParam(request.getSeatIds(),
                request.getScreeningId(), request.getUsername());

        reservationCommandUseCase.reserve(param);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
