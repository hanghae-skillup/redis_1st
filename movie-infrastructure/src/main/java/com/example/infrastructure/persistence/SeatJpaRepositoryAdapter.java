package com.example.infrastructure.persistence;

import com.example.application.port.out.SeatRepositoryPort;
import com.example.domain.model.entity.Seat;
import com.example.infrastructure.db.SeatJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class SeatJpaRepositoryAdapter implements SeatRepositoryPort {

    private final SeatJpaRepository seatJpaRepository;


    @Override
    public List<Seat> findAll() {
        return seatJpaRepository.findAll();
    }
}
