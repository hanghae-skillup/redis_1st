package com.movie.domain.service;

import com.movie.common.exception.EntityNotFoundException;
import com.movie.common.service.RateLimitService;
import com.movie.domain.entity.Reservation;
import com.movie.domain.entity.Schedule;
import com.movie.domain.entity.Seat;
import com.movie.domain.entity.User;
import com.movie.domain.repository.ReservationRepository;
import com.movie.domain.repository.ScheduleRepository;
import com.movie.domain.repository.SeatRepository;
import com.movie.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final SeatRepository seatRepository;
    private final RateLimitService rateLimitService;

    @Transactional
    public Reservation reserve(Long userId, Long scheduleId, List<Long> seatIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User"));
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Schedule"));

        // Rate limit check for user and schedule combination
        rateLimitService.checkUserReservationRateLimit(userId, schedule.getStartTime().toString());

        List<Seat> seats = seatRepository.findAllById(seatIds);
        if (seats.size() != seatIds.size()) {
            throw new EntityNotFoundException("Seat");
        }

        String reservationNumber = generateReservationNumber();
        Reservation reservation = Reservation.builder()
                .reservationNumber(reservationNumber)
                .user(user)
                .schedule(schedule)
                .seats(seats)
                .build();

        return reservationRepository.save(reservation);
    }

    private String generateReservationNumber() {
        return UUID.randomUUID().toString();
    }
} 