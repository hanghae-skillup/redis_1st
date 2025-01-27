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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final SeatRepository seatRepository;
    private final ReservationRepository reservationRepository;
    private final MessageService messageService;

    @Transactional
    @DistributedLock(key = "'reservation:' + #scheduleId + ':' + #seatId")
    public String reserve(Long userId, Long scheduleId, Long seatId) {
        // Check if entities exist
        userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found"));

        seatRepository.findById(seatId)
                .orElseThrow(() -> new IllegalArgumentException("Seat not found"));

        validateSeatAvailability(scheduleId, seatId);
        validateMaxReservationLimit(userId, scheduleId);
        validateSeatContinuity(userId, scheduleId, seatId);

        String reservationNumber = generateReservationNumber();
        
        Reservation reservation = Reservation.builder()
                .userId(userId)
                .scheduleId(scheduleId)
                .seatId(seatId)
                .reservationNumber(reservationNumber)
                .build();

        reservation = reservationRepository.save(reservation);
        
        // FCM 메시지 발송 (Mock)
        messageService.sendReservationComplete(reservation);

        return reservationNumber;
    }

    private void validateSeatAvailability(Long scheduleId, Long seatId) {
        if (reservationRepository.existsByScheduleIdAndSeatIdAndStatus(scheduleId, seatId, ReservationStatus.RESERVED)) {
            throw new IllegalStateException("Seat is already reserved");
        }
    }

    private void validateMaxReservationLimit(Long userId, Long scheduleId) {
        long reservationCount = reservationRepository.countByUserIdAndScheduleIdAndStatus(userId, scheduleId, ReservationStatus.RESERVED);
        if (reservationCount >= 5) {
            throw new IllegalStateException("Maximum reservation limit reached");
        }
    }

    private void validateSeatContinuity(Long userId, Long scheduleId, Long seatId) {
        List<Reservation> existingReservations = reservationRepository.findByUserIdAndScheduleIdAndStatus(userId, scheduleId, ReservationStatus.RESERVED);
        if (!existingReservations.isEmpty()) {
            Seat newSeat = seatRepository.findById(seatId)
                    .orElseThrow(() -> new IllegalArgumentException("Seat not found"));
            
            // 기존 예약된 좌석들과 연속성 체크
            boolean isContinuous = existingReservations.stream()
                    .map(reservation -> seatRepository.findById(reservation.getSeatId())
                            .orElseThrow(() -> new IllegalArgumentException("Seat not found")))
                    .anyMatch(existingSeat -> isAdjacent(existingSeat, newSeat));
                        
            if (!isContinuous) {
                throw new IllegalStateException("Seats must be continuous");
            }
        }
    }

    private boolean isAdjacent(Seat seat1, Seat seat2) {
        if (seat1.getSeatRow().equals(seat2.getSeatRow())) {
            return Math.abs(seat1.getSeatColumn() - seat2.getSeatColumn()) == 1;
        }
        return false;
    }

    private String generateReservationNumber() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
} 