package org.example.domain.reservationseat;

import org.assertj.core.api.Assertions;
import org.example.domain.seat.Col;
import org.example.domain.seat.Row;
import org.example.dto.SeatsDto;
import org.example.exception.SeatException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ReservationSeatTest {

    @Test
    @DisplayName("이미 예매한 좌석과 예매하려는 좌석의 합이 5개가 넘으면 예외가 발생한다.")
    void exceedTotalReservationSeat_ThrowException() {
        // given
        List<SeatsDto> reservationSeats = new ArrayList<>();
        reservationSeats.add(new SeatsDto(Row.ROW_A, Col.COL_1));
        reservationSeats.add(new SeatsDto(Row.ROW_A, Col.COL_2));
        reservationSeats.add(new SeatsDto(Row.ROW_A, Col.COL_3));

        List<SeatsDto> reservedSeats = new ArrayList<>();
        reservedSeats.add(new SeatsDto(Row.ROW_A, Col.COL_4));
        reservedSeats.add(new SeatsDto(Row.ROW_A, Col.COL_5));
        reservedSeats.add(new SeatsDto(Row.ROW_B, Col.COL_1));

        // when
        Throwable thrown = assertThrows(SeatException.class, () ->
                ReservationSeat.validateCountExceeded(reservationSeats, reservedSeats)
        );

        // then
        Assertions.assertThat(thrown)
                .isInstanceOf(SeatException.class)
                .hasMessage("최대 예약 가능한 좌석을 초과했습니다.");
    }

    @Test
    @DisplayName("이미 예매한 좌석을 같은 사용자가 또 예매하려고 하면 예외가 발생한다.")
    void containsReservedSeat_ThrowException() {
        // given
        List<SeatsDto> reservationSeats = new ArrayList<>();
        reservationSeats.add(new SeatsDto(Row.ROW_A, Col.COL_1));
        reservationSeats.add(new SeatsDto(Row.ROW_A, Col.COL_2));

        List<SeatsDto> reservedSeats = new ArrayList<>();
        reservedSeats.add(new SeatsDto(Row.ROW_A, Col.COL_2));
        reservedSeats.add(new SeatsDto(Row.ROW_A, Col.COL_3));

        // when
        Throwable thrown = assertThrows(SeatException.class, () ->
                ReservationSeat.containsReservedSeat(reservationSeats, reservedSeats)
        );

        // then
        Assertions.assertThat(thrown)
                .isInstanceOf(SeatException.class)
                .hasMessage("이미 예약된 좌석입니다.");
    }

    @Test
    @DisplayName("같은 사용자가 이미 예매한 좌석들과 다른 행을 예매하려고 하면 예외가 발생한다.")
    void notSameRow_ThrowException() {
        // given
        List<SeatsDto> reservationSeats = new ArrayList<>();
        reservationSeats.add(new SeatsDto(Row.ROW_A, Col.COL_1));
        reservationSeats.add(new SeatsDto(Row.ROW_A, Col.COL_2));

        List<SeatsDto> reservedSeats = new ArrayList<>();
        reservedSeats.add(new SeatsDto(Row.ROW_B, Col.COL_3));
        reservedSeats.add(new SeatsDto(Row.ROW_B, Col.COL_4));

        // when
        Throwable thrown = assertThrows(SeatException.class, () ->
                ReservationSeat.isSameRow(reservationSeats, reservedSeats)
        );

        // then
        Assertions.assertThat(thrown)
                .isInstanceOf(SeatException.class)
                .hasMessage("연속된 좌석만 예약할 수 있습니다. 행이 다릅니다.");
    }

    @Test
    @DisplayName("같은 사용자가 이미 예매한 좌석들과 연속적이지 않은 열을 예매하려고 하면 예외가 발생한다.")
    void notContinuousCol_ThrowException() {
        // given
        List<SeatsDto> reservationSeats = new ArrayList<>();
        reservationSeats.add(new SeatsDto(Row.ROW_A, Col.COL_1));
        reservationSeats.add(new SeatsDto(Row.ROW_A, Col.COL_2));

        List<SeatsDto> reservedSeats = new ArrayList<>();
        reservedSeats.add(new SeatsDto(Row.ROW_A, Col.COL_4));
        reservedSeats.add(new SeatsDto(Row.ROW_A, Col.COL_5));

        // when
        Throwable thrown = assertThrows(SeatException.class, () ->
                ReservationSeat.isContinuousCol(reservationSeats, reservedSeats)
        );

        // then
        Assertions.assertThat(thrown)
                .isInstanceOf(SeatException.class)
                .hasMessage("연속된 좌석만 예약할 수 있습니다. 열이 연속되지 않았습니다.");
    }

}