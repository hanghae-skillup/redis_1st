package com.movie.domain.movie;

import com.movie.domain.movie.dto.command.ReservationCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    public void makeReservation(ReservationCommand.Reserve reserve) {
        // 자리 검색

    }

}
