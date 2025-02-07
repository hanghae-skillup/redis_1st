package org.example.service.reservation;

import org.example.domain.reservation.Reservation;
import org.example.domain.reservationseat.ReservationSeat;
import org.example.domain.seat.Col;
import org.example.domain.seat.Row;
import org.example.domain.seat.Seat;
import org.example.dto.SeatsDto;
import org.example.exception.SeatException;
import org.example.repository.ReservationJpaRepository;
import org.example.repository.ReservationSeatRepository;
import org.example.repository.SeatJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.example.baseresponse.BaseResponseStatus.CONCURRENT_RESERVATION_ERROR;
import static org.example.baseresponse.BaseResponseStatus.UNAVAILABLE_SEAT_ERROR;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {
    @InjectMocks
    private SaveReservationService saveReservationService;

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationJpaRepository reservationJpaRepository;

    @Mock
    private ReservationSeatRepository reservationSeatRepository;

    @Mock
    private SeatJpaRepository seatJpaRepository;


    @Test
    @DisplayName("예약된 좌석이 없는 경우 예약 성공")
    void reserve_Success() {
        // Given
        Long userId = 1L;
        Long screenScheduleId = 1L;
        List<Seat> seats = new ArrayList<>();
        seats.add(Seat.of(1L, Row.ROW_A, Col.COL_1, 1L));
        seats.add(Seat.of(2L, Row.ROW_A, Col.COL_2, 1L));

        // Mock: 좌석이 예약되지 않았음을 가정
        when(reservationSeatRepository.findReservedSeatBySeatId(anyLong(), anyLong()))
                .thenReturn(Optional.empty()); // 예약된 좌석 없음

        Reservation reservation = new Reservation(userId, screenScheduleId);
        when(reservationJpaRepository.save(ArgumentMatchers.any()))
                .thenReturn(reservation);

        // When
        saveReservationService.saveReservationWithTransaction(userId, screenScheduleId, seats);

        // Then
        verify(reservationJpaRepository, times(1)).save(ArgumentMatchers.any());
        verify(reservationSeatRepository, times(2)).save(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("예약된 좌석이 없는 경우 예외 발생")
    void reserve_Fail() {
        // Given
        Long userId = 1L;
        Long screenScheduleId = 1L;
        List<Seat> seats = new ArrayList<>();
        seats.add(Seat.of(1L, Row.ROW_A, Col.COL_1, 1L));
        seats.add(Seat.of(2L, Row.ROW_A, Col.COL_2, 1L));

        // Mock: 첫 번째 좌석이 이미 예약됨
        when(reservationSeatRepository.findReservedSeatBySeatId(screenScheduleId, 1L))
                .thenReturn(Optional.of(new ReservationSeat(1L, 1L)));

        // When & Then
        SeatException thrown = assertThrows(SeatException.class, () ->
                saveReservationService.saveReservationWithTransaction(userId, screenScheduleId, seats)
        );

        Assertions.assertEquals(CONCURRENT_RESERVATION_ERROR, thrown.getExceptionStatus());
        verify(reservationJpaRepository, never()).save(ArgumentMatchers.any()); // 예약이 저장되지 않아야 함
        verify(reservationSeatRepository, never()).save(ArgumentMatchers.any()); // 좌석도 저장되지 않아야 함
    }

    @Test
    @DisplayName("Seat 테이블에 좌석이 존재하면 조회에 성공한다.")
    void getSeat_Success() {
        // Given
        Long screenRoomId = 1L;
        List<SeatsDto> reservationSeats = List.of(
                new SeatsDto(Row.ROW_A, Col.COL_1),
                new SeatsDto(Row.ROW_A, Col.COL_2)
        );

        // Mock: 존재하는 좌석 설정
        given(seatJpaRepository.findSeats(screenRoomId, Row.ROW_A, Col.COL_1))
                .willReturn(Optional.of(new Seat(1L,Row.ROW_A, Col.COL_1, screenRoomId)));

        given(seatJpaRepository.findSeats(screenRoomId, Row.ROW_A, Col.COL_2))
                .willReturn(Optional.of(new Seat(2L, Row.ROW_A, Col.COL_2, screenRoomId)));

        // When
        List<Seat> seats = reservationService.validateReservedSeats(screenRoomId, reservationSeats);

        // Then
        Assertions.assertEquals(2, seats.size());
        Assertions.assertEquals("A", seats.get(0).getRow().getRow());
        Assertions.assertEquals(1, seats.get(0).getCol().getColumn());
        Assertions.assertEquals("A", seats.get(1).getRow().getRow());
        Assertions.assertEquals(2, seats.get(1).getCol().getColumn());
    }

    @Test
    @DisplayName("Seat 테이블에 좌석이 존재하지 않으면 예외가 발생한다.")
    void getSeat_Fail() {
        // Given
        Long screenRoomId = 1L;
        List<SeatsDto> reservationSeats = List.of(
                new SeatsDto(Row.ROW_A, Col.COL_1),
                new SeatsDto(Row.ROW_A, Col.COL_2)
        );

        // Mock: 존재하는 좌석 설정
        given(seatJpaRepository.findSeats(screenRoomId, Row.ROW_A, Col.COL_1))
                .willReturn(Optional.of(new Seat(1L,Row.ROW_A, Col.COL_1, screenRoomId)));

        given(seatJpaRepository.findSeats(screenRoomId, Row.ROW_A, Col.COL_2))
                .willReturn(Optional.empty());

        // When & Then
        SeatException thrown = assertThrows(SeatException.class, () ->
                reservationService.validateReservedSeats(screenRoomId, reservationSeats)
        );

        Assertions.assertEquals(UNAVAILABLE_SEAT_ERROR, thrown.getExceptionStatus());
    }


}