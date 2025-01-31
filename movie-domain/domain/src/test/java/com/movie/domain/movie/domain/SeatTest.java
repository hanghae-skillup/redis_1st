package com.movie.domain.movie.domain;

import com.movie.common.enums.AxisY;
import com.movie.common.exception.ApplicationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class SeatTest {

    @DisplayName("좌석 5개 이상 예약 시도 시, 예외를 발생시킨다.")
    @Test
    void givenOver5Seat_whenVerifySeatSize_thenThrowException() {
        // given
        int seatSize = 6;

        // when
        Throwable t = catchThrowable(() -> Seat.isExceeded(seatSize));

        // then
        assertThat(t)
                .isInstanceOf(ApplicationException.class)
                .hasMessage("seats are exceeded over 5 - requested seats : %d".formatted(seatSize));
    }


    @DisplayName("연속적인 자리 예약을 시도 시, 예외를 발생시키지 않는다.")
    @Test
    void givenNotConsecutiveSeats_whenVerifyIfConsecutive_thenDoNotThrowException() {
        // given
        List<Seat> seats = new ArrayList<>(List.of(
                Seat.of(1L, "A1", AxisY.A, 1),
                Seat.of(2L, "A2", AxisY.A, 2),
                Seat.of(3L, "A3", AxisY.A, 3),
                Seat.of(4L, "A4", AxisY.A, 4),
                Seat.of(5L, "A5", AxisY.A, 5)
        ));

        List<Seat> requestingSeatsB = new ArrayList<>(List.of(
                Seat.of("A1", AxisY.A, 1),
                Seat.of("A2", AxisY.A, 2),
                Seat.of("C4", AxisY.C, 4),
                Seat.of("C5", AxisY.C, 5),
                Seat.of("C3", AxisY.C, 3)
        ));

        List<Seat> requestingSeatsC = new ArrayList<>(List.of(
                Seat.of("A1", AxisY.A, 1),
                Seat.of("A2", AxisY.A, 2),
                Seat.of("C4", AxisY.C, 4),
                Seat.of("C5", AxisY.C, 5)
        ));

        // when && then
        assertDoesNotThrow(() -> Seat.isConsecutive(seats));
        assertDoesNotThrow(() -> Seat.isConsecutive(requestingSeatsB));
        assertDoesNotThrow(() -> Seat.isConsecutive(requestingSeatsC));
    }

    @DisplayName("연속적이지 않은 자리의 유효성을 체크하며, 유효하지 않으면 예외를 발생시킨다.")
    @Test
    void givenNotConsecutiveSeats_whenVerifyIfConsecutive_thenThrowException() {
        // given
        List<Seat> requestingSeatsA = new ArrayList<>(List.of(
                Seat.of("A1", AxisY.A, 1),
                Seat.of("A2", AxisY.A, 2),
                Seat.of("A4", AxisY.A, 4),
                Seat.of("A5", AxisY.A, 5)
        ));

        List<Seat> requestingSeatsB = new ArrayList<>(List.of(
                Seat.of("A1", AxisY.A, 1),
                Seat.of("A2", AxisY.A, 2),
                Seat.of("B1", AxisY.B, 1),
                Seat.of("B2", AxisY.B, 2),
                Seat.of("C2", AxisY.C, 2)
        ));

        List<Seat> requestingSeatsC = new ArrayList<>(List.of(
                Seat.of("A1", AxisY.A, 1),
                Seat.of("A2", AxisY.A, 2),
                Seat.of("C4", AxisY.C, 4),
                Seat.of("C5", AxisY.C, 5),
                Seat.of("E3", AxisY.E, 3)
        ));

        List<Seat> requestingSeatsD = new ArrayList<>(List.of(
                Seat.of("A1", AxisY.A, 1),
                Seat.of("C4", AxisY.C, 4),
                Seat.of("E3", AxisY.E, 3)
        ));

        // when && then
        Throwable t1 = catchThrowable(() -> Seat.isConsecutive(requestingSeatsA));
        Throwable t2 = catchThrowable(() -> Seat.isConsecutive(requestingSeatsB));
        Throwable t3 = catchThrowable(() -> Seat.isConsecutive(requestingSeatsC));
        Throwable t4 = catchThrowable(() -> Seat.isConsecutive(requestingSeatsD));

        List<String> seatNumbersA = requestingSeatsA.stream().map(Seat::getSeatNumber).toList();
        List<String> seatNumbersB = requestingSeatsB.stream().map(Seat::getSeatNumber).toList();
        List<String> seatNumbersC = requestingSeatsC.stream().map(Seat::getSeatNumber).toList();
        List<String> seatNumbersD = requestingSeatsD.stream().map(Seat::getSeatNumber).toList();

        assertThat(t1)
                .isInstanceOf(ApplicationException.class)
                .hasMessage("seats are not consecutive - %s".formatted(seatNumbersA));

        assertThat(t2)
                .isInstanceOf(ApplicationException.class)
                .hasMessage("seats are not consecutive - %s".formatted(seatNumbersB));

        assertThat(t3)
                .isInstanceOf(ApplicationException.class)
                .hasMessage("seats are not consecutive - %s".formatted(seatNumbersC));

        assertThat(t4)
                .isInstanceOf(ApplicationException.class)
                .hasMessage("seats are not consecutive - %s".formatted(seatNumbersD));
    }

}