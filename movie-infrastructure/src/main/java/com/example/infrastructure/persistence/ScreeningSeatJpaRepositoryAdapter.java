package com.example.infrastructure.persistence;

import com.example.application.port.out.ScreeningSeatRepositoryPort;
import com.example.domain.model.entity.Screening;
import com.example.domain.model.entity.ScreeningSeat;
import com.example.infrastructure.db.ScreeningSeatJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class ScreeningSeatJpaRepositoryAdapter implements ScreeningSeatRepositoryPort {

    private final ScreeningSeatJpaRepository screeningSeatJpaRepository;

    @Override
    public List<ScreeningSeat> findByScreeningAndSeatIds(Screening screening, List<Long> seatIds) {
        return screeningSeatJpaRepository.findByScreeningAndSeat_IdIn(screening, seatIds);
    }

    @Override
    public void save(ScreeningSeat screeningSeat) {
        screeningSeatJpaRepository.save(screeningSeat);
    }

    @Override
    public long count() {
        return screeningSeatJpaRepository.count();
    }

    @Override
    @Transactional
    public void resetAllReservations() {
        screeningSeatJpaRepository.resetAllReservations();
    }

    @Override
    public long countReservedSeats(Long screeningId) {
        return screeningSeatJpaRepository.countByScreeningIdAndReservedIsTrue(screeningId);
    }

}
