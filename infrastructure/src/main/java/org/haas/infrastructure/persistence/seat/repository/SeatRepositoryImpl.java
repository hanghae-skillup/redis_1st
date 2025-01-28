package org.haas.infrastructure.persistence.seat.repository;

import lombok.RequiredArgsConstructor;
import org.haas.core.domain.seat.Seat;
import org.haas.core.domain.seat.repository.SeatRepository;
import org.haas.infrastructure.persistence.seat.entity.SeatEntity;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class SeatRepositoryImpl implements SeatRepository {

    private final SeatJpaRepository seatJpaRepository;

    @Override
    public Seat findByRowAndColumn(int row, int column) {
//        SeatEntity seatEntity = seatJpaRepository.findByRowAndColumn(row, column)
//                .orElse(null);
//        if (seatEntity != null) {
//            return seatMapper.toDomain(seatEntity);
//        }
        return null;
    }
}
