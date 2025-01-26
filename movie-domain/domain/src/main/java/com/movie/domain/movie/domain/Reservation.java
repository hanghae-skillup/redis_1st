package com.movie.domain.movie.domain;

import com.movie.common.exception.ApplicationException;
import com.movie.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    private Long scheduleId;
    private Long seatId;
    private Long userId;
    private LocalDateTime reservedAt;

    public Reservation(Long scheduleId, Long seatId, Long userId, LocalDateTime reservedAt) {
        this.scheduleId = scheduleId;
        this.seatId = seatId;
        this.userId = userId;
        this.reservedAt = reservedAt;
    }

    public static Reservation of(Long scheduleId, Long seatId, Long userId, LocalDateTime reservedAt) {
        return new Reservation(scheduleId, seatId, userId, reservedAt);
    }

    public static void isAlreadyReserved(List<Reservation> reservations, List<Seat> seats) {
        List<Long> reservedSeatIds = reservations.stream()
                .filter(reservation -> reservation.getUserId() != null)
                .map(Reservation::getSeatId)
                .toList();
        List<String> reservedSeatNumbers = seats.stream()
                .filter(seat -> reservedSeatIds.contains(seat.getId()))
                .map(Seat::getSeatNumber)
                .toList();
        if (!reservedSeatNumbers.isEmpty()) {
            throw new ApplicationException(
                    ErrorCode.UNABLE_TO_RESERVE,
                    "reservation contains already reserved seats: " + String.join(", ", reservedSeatNumbers)
            );
        }
    }
}
