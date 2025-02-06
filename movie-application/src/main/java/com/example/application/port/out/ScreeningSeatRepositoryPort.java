package com.example.application.port.out;

import com.example.domain.model.entity.Screening;
import com.example.domain.model.entity.ScreeningSeat;
import java.util.List;

public interface ScreeningSeatRepositoryPort {
    List<ScreeningSeat> findByScreeningAndSeatIds(Screening screening, List<Long> seatIds);
    void saveAndFlush(ScreeningSeat screeningSeat);
    long count();
    void resetAllReservations();
    long countReservedSeats(Long screeningId);
}
