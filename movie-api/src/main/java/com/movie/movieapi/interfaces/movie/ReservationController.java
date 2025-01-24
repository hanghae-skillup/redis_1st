package com.movie.movieapi.interfaces.movie;

import com.movie.domain.response.Response;
import com.movie.domain.facade.ReservationFacade;
import com.movie.movieapi.interfaces.movie.dto.ReservationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationFacade reservationFacade;

    @PostMapping
    public Response<Void> makeReservation(
            @RequestBody ReservationDto.Reserve reserve, @RequestHeader("token") String token
    ) {
        reservationFacade.makeReservation(reserve.toCommand(token));
        return Response.success();
    }

}
