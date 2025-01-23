package com.movie.movieapi.interfaces.movie;

import com.movie.domain.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {



    @PostMapping
    public Response<Void> makeReservation() {


        return Response.success();
    }

}
