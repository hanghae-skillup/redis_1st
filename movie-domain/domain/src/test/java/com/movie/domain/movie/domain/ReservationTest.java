package com.movie.domain.movie.domain;

import com.movie.common.enums.AxisY;
import com.movie.common.exception.ApplicationException;
import com.movie.common.exception.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    @DisplayName("예약이 완료된 좌석을 선택한다면, 예외를 발생시킨다.")
    @Test
    void givenReservationsAndSeats_whenSeatsAreAlreadyReserved_thenThrowsException() {
        List<Reservation> reservations = List.of(
                Reservation.of(1L, 1L, 1L, LocalDateTime.now()),
                Reservation.of(1L, 2L, 1L, LocalDateTime.now()),
                Reservation.of(1L, 3L, 1L, LocalDateTime.now())
        );
        List<Seat> seats = List.of(
                Seat.of(1L, "A1", AxisY.A, 1),
                Seat.of(2L, "A2", AxisY.A, 2),
                Seat.of(2L, "A3", AxisY.A, 3)
        );

        ApplicationException thrown = assertThrows(ApplicationException.class, () -> {
            Reservation.isAlreadyReserved(reservations, seats);
        });
        assertEquals(ErrorCode.UNABLE_TO_RESERVE, thrown.getErrorCode());
        assertTrue(thrown.getMessage().contains("reservation contains already reserved seats:"));
    }

    @DisplayName("예약이 완료되지 않은 좌석 선택시, 예외를 발생시키지 않는다.")
    @Test
    void givenReservationsAndSeats_whenSeatsAreNotReserved_thenDoNotThrowException() {
        List<Reservation> reservations = List.of(
                Reservation.of(1L, 1L, null, null),
                Reservation.of(1L, 2L, null, null),
                Reservation.of(1L, 3L, null, null)
        );
        List<Seat> seats = List.of(
                Seat.of(1L, "A1", AxisY.A, 1),
                Seat.of(2L, "A2", AxisY.A, 2),
                Seat.of(2L, "A3", AxisY.A, 3)
        );

        assertDoesNotThrow(() -> {
            Reservation.isAlreadyReserved(reservations, seats);
        });
    }

}