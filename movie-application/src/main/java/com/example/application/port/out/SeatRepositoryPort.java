package com.example.application.port.out;

import com.example.domain.model.entity.Seat;
import java.util.List;

public interface SeatRepositoryPort {
    List<Seat> findAll();
}
