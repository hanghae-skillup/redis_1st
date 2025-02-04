package com.movie.domain.movie;

import com.movie.domain.movie.domain.Reservation;
import com.movie.domain.movie.dto.command.ReservationCommand;

import java.util.List;

public interface ReservationRepository {

    List<Reservation> getReservations(ReservationCommand.Get get);

    List<Reservation> makeReservation(ReservationCommand.Reserve reserve);

}
