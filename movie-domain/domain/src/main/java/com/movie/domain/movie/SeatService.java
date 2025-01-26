package com.movie.domain.movie;

import com.movie.domain.movie.domain.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;

    public List<Seat> getSeats(List<Long> seatIds) {
        return seatRepository.getSeats(seatIds);
    }

}
