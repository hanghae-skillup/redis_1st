package com.movie.application.service;

import com.movie.aop.DistributedLock;
import com.movie.domain.entity.Reservation;
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
    public String reserve(Long userId, Long scheduleId, Long seatId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new BusinessException(ErrorCode.SCHEDULE_NOT_FOUND));
        
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new BusinessException(ErrorCode.SEAT_NOT_FOUND));

        // 이미 예약된 좌석인지 확인
        if (reservationRepository.existsByScheduleAndSeat(schedule, seat)) {
            throw new BusinessException(ErrorCode.SEAT_ALREADY_RESERVED);
        }

        // 예약 번호 생성
        String reservationNumber = generateReservationNumber();

        // 예약 생성
        Reservation reservation = Reservation.builder()
                .reservationNumber(reservationNumber)
                .user(user)
                .schedule(schedule)
                .seat(seat)
                .createdBy("SYSTEM")
                .createdAt(LocalDateTime.now())
                .updatedBy("SYSTEM")
                .updatedAt(LocalDateTime.now())
                .build();

        reservationRepository.save(reservation);
        
        return reservationNumber;
    }

    @Transactional(readOnly = true)
    public List<Reservation> getUserReservations(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        return reservationRepository.findByUser(user);
    }

    @Transactional(readOnly = true)
    public Reservation getReservation(String reservationNumber) {
        return reservationRepository.findByReservationNumber(reservationNumber)
                .orElseThrow(() -> new BusinessException(ErrorCode.RESERVATION_NOT_FOUND));
    }

    @Transactional
    public void cancelReservation(String reservationNumber) {
        Reservation reservation = reservationRepository.findByReservationNumber(reservationNumber)
                .orElseThrow(() -> new BusinessException(ErrorCode.RESERVATION_NOT_FOUND));
        reservationRepository.delete(reservation);
    }

    @Transactional(readOnly = true)
    public List<Seat> getAvailableSeats(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new BusinessException(ErrorCode.SCHEDULE_NOT_FOUND));
        return seatRepository.findAvailableSeats(schedule);
    }

    private String generateReservationNumber() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
} 