package com.movie.storage.movie.repository;

import com.movie.domain.movie.SeatRepository;
import com.movie.domain.movie.domain.Seat;
import com.movie.storage.mapper.ModelMapper;
import com.movie.storage.movie.entity.SeatEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SeatRepositoryImpl implements SeatRepository {

    private final SeatJpaRepository seatJpaRepository;

    @Override
    public List<Seat> getSeats(List<Long> seatIds) {
        seatIds.sort(Comparator.naturalOrder());  // 데드락 방지를 위한 id 정렬
        List<SeatEntity> seats = seatJpaRepository.findByIdIn(seatIds);
        return seats.stream().map(ModelMapper.SeatMapper::from).toList();
    }

}
