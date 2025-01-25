package com.hanghae.application.port.out;

import com.hanghae.domain.model.TicketReservation;

import java.util.List;

public interface TicketReservationRepositoryPort {
    List<TicketReservation> saveMovieReservations(List<TicketReservation> ticketReservations);
}
