package org.haas.application.service;

public interface SeatService {

    boolean reserveSeat(int row, int column);

    boolean reservationAvailable(int row, int column);
}
