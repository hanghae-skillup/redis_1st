package org.example.domain.seat;

import org.assertj.core.api.Assertions;
import org.example.dto.SeatsDto;
import org.example.exception.SeatException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SeatTest {
    @Test
    @DisplayName("예매하려는 좌석이 5개가 넘을 때 예외가 발생한다.")
    void exceedCount_6Seat_ThrowException() {
        // given
        int seatSize = 6;

        // when
        Throwable thrown = assertThrows(SeatException.class, () ->
                Seat.validateSeatCount(seatSize)
        );

        // then
        Assertions.assertThat(thrown)
                .isInstanceOf(SeatException.class)
                .hasMessage("예약 가능한 좌석 개수가 아닙니다.");
    }

    @Test
    @DisplayName("예매하려는 좌석이 0개일 때 예외가 발생한다.")
    void exceedCount_0Seat_ThrowException() {
        // given
        int seatSize = 0;

        // when
        Throwable thrown = assertThrows(SeatException.class, () ->
                Seat.validateSeatCount(seatSize)
        );

        // then
        Assertions.assertThat(thrown)
                .isInstanceOf(SeatException.class)
                .hasMessage("예약 가능한 좌석 개수가 아닙니다.");
    }

    @Test
    @DisplayName("예매하려는 좌석들의 행이 다르면 예외가 발생한다.")
    void continuousSeats_NotSameRow_ThrowException() {
        // given
        List<SeatsDto> seats = new ArrayList<>();
        seats.add(new SeatsDto(Row.ROW_A, Col.COL_1));
        seats.add(new SeatsDto(Row.ROW_B, Col.COL_1));

        // when
        Throwable thrown = assertThrows(SeatException.class, () ->
                Seat.validateContinuousSeats(seats)
        );

        // then
        Assertions.assertThat(thrown)
                .isInstanceOf(SeatException.class)
                .hasMessage("연속된 좌석만 예약할 수 있습니다. 행이 다릅니다.");
    }

    @Test
    @DisplayName("예매하려는 좌석들이 연속적이지 않으면 다르면 예외가 발생한다.")
    void continuousSeats_NotContinuousCol_ThrowException() {
        // given
        List<SeatsDto> seats = new ArrayList<>();
        seats.add(new SeatsDto(Row.ROW_A, Col.COL_1));
        seats.add(new SeatsDto(Row.ROW_A, Col.COL_3));

        // when
        Throwable thrown = assertThrows(SeatException.class, () ->
                Seat.validateContinuousSeats(seats)
        );

        // then
        Assertions.assertThat(thrown)
                .isInstanceOf(SeatException.class)
                .hasMessage("연속된 좌석만 예약할 수 있습니다. 열이 연속되지 않았습니다.");
    }
}