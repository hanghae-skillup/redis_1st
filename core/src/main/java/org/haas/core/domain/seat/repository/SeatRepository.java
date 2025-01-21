package org.haas.core.domain.seat.repository;

import org.haas.core.domain.seat.Seat;

import java.util.Optional;

public interface SeatRepository {

    Seat findByRowAndColumn(int row, int column);
}
