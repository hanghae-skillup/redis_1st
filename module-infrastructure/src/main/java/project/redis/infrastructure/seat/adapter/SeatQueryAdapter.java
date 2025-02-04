package project.redis.infrastructure.seat.adapter;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.redis.application.seat.port.outbound.SeatQueryPort;
import project.redis.domain.seat.Seat;
import project.redis.infrastructure.seat.mapper.SeatInfraMapper;
import project.redis.infrastructure.seat.respository.SeatJpaRepository;


@Transactional(readOnly = true)
@Component
@RequiredArgsConstructor
public class SeatQueryAdapter implements SeatQueryPort {

    private final SeatJpaRepository seatJpaRepository;

    @Override
    public List<Seat> getSeats(List<UUID> seatIds) {
        return seatJpaRepository.findByIdIn(seatIds).stream()
                .map(SeatInfraMapper::toSeatOnly)
                .toList();
    }
}
