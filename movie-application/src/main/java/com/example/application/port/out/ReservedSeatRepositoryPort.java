package com.example.application.port.out;

import com.example.domain.model.entity.ReservedSeat;
import java.util.List;

public interface ReservedSeatRepositoryPort {
    void saveAll(List<ReservedSeat> reservedSeat);
}
