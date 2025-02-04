package com.movie.domain.service;

import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.RateLimiter;
import com.movie.api.exception.RateLimitExceededException;
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
    private final LoadingCache<String, RateLimiter> userReservationRateLimitCache;

    @Transactional
    public Reservation reserve(Long userId, Long scheduleId, List<Long> seatIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found"));

        // Rate limit check for user and schedule combination
        String rateLimitKey = userId + ":" + schedule.getStartTime();
        RateLimiter rateLimiter = userReservationRateLimitCache.getUnchecked(rateLimitKey);
        if (!rateLimiter.tryAcquire()) {
            throw new RateLimitExceededException("Too many reservation attempts for this schedule. Please wait 5 minutes.");
        }

        List<Seat> seats = seatRepository.findAllById(seatIds);
        if (seats.size() != seatIds.size()) {
            throw new IllegalArgumentException("Some seats not found");
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