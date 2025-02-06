package com.example.infrastructure.db.persistence;

import com.example.application.port.out.ReservedSeatRepositoryPort;
import com.example.domain.model.entity.ReservedSeat;
import com.example.infrastructure.db.ReservedSeatJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReservedSeatJpaRepositoryAdapter implements ReservedSeatRepositoryPort {

    private final ReservedSeatJpaRepository reservedSeatJpaRepository;

    @Override
    public void saveAll(List<ReservedSeat> reservedSeat) {
        reservedSeatJpaRepository.saveAll(reservedSeat);
    }

}
