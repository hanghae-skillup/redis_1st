package com.movie.application.service;

import com.movie.domain.aop.DistributedLock;
import com.movie.domain.entity.Reservation;
import com.movie.domain.entity.ReservationStatus;
import com.movie.domain.entity.Schedule;
import com.movie.domain.entity.Seat;
import com.movie.domain.entity.User;
import com.movie.domain.repository.ReservationRepository;
import com.movie.domain.repository.ScheduleRepository;
import com.movie.domain.repository.SeatRepository;
import com.movie.domain.repository.UserRepository;
import com.movie.exception.BusinessException;
import com.movie.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final SeatRepository seatRepository;

    @Transactional
    @DistributedLock(key = "'reservation:' + #scheduleId + ':' + #seatId")
    @CacheEvict(value = {"reservations", "availableSeats"}, allEntries = true)
    public String reserve(Long userId, Long scheduleId, Long seatId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new BusinessException(ErrorCode.SCHEDULE_NOT_FOUND));
        
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new BusinessException(ErrorCode.SEAT_NOT_FOUND));

        // 이미 예약된 좌석인지 확인
        if (reservationRepository.existsByScheduleIdAndSeatId(scheduleId, seatId)) {
            throw new BusinessException(ErrorCode.SEAT_ALREADY_RESERVED);
        }

        // 예약 번호 생성
        String reservationNumber = generateReservationNumber();
        
        // 예약 생성
        Reservation reservation = Reservation.builder()
                .userId(userId)
                .scheduleId(scheduleId)
                .seatId(seatId)
                .reservationNumber(reservationNumber)
                .build();
        
        reservationRepository.save(reservation);
        return reservationNumber;
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "reservations", key = "'user:' + #userId")
    public List<Reservation> getUserReservations(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        return reservationRepository.findByUserId(userId);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "reservations", key = "'number:' + #reservationNumber")
    public Reservation getReservation(String reservationNumber) {
        return reservationRepository.findByReservationNumber(reservationNumber)
                .orElseThrow(() -> new BusinessException(ErrorCode.RESERVATION_NOT_FOUND));
    }

    @Transactional
    @CacheEvict(value = {"reservations", "availableSeats"}, allEntries = true)
    public void cancelReservation(String reservationNumber) {
        Reservation reservation = reservationRepository.findByReservationNumber(reservationNumber)
                .orElseThrow(() -> new BusinessException(ErrorCode.RESERVATION_NOT_FOUND));
        reservationRepository.delete(reservation);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "availableSeats", key = "'schedule:' + #scheduleId")
    public List<Seat> getAvailableSeats(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new BusinessException(ErrorCode.SCHEDULE_NOT_FOUND));
        return seatRepository.findAvailableSeats(schedule);
    }

    private String generateReservationNumber() {
        return UUID.randomUUID().toString();
    }
} 