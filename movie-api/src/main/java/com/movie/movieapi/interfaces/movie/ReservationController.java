package com.movie.movieapi.interfaces.movie;

import com.movie.application.movie.facade.ReservationFacade;
import com.movie.common.response.Response;
import com.movie.movieapi.interfaces.movie.dto.ReservationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationFacade reservationFacade;

    @PostMapping
    public Response<Void> makeReservation(
            @RequestBody ReservationDto.Reserve reserve, @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ) {
        reservationFacade.makeReservationsByFunctionalLock(reserve.toCommand(token));
        return Response.success();
    }

}
