package com.movie.domain.movie;

import com.movie.domain.movie.domain.Reservation;
import com.movie.domain.movie.dto.command.ReservationCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @InjectMocks
    private ReservationService sut;

    @Mock
    private ReservationRepository reservationRepository;

    @DisplayName("스케줄 id와 좌석 ids 를 이용하여, 좌석에 따른 예약 정보 목록을 조회한다.")
    @Test
    void givenScheduleIdAndSeatIds_whenRequestingReservationsBySeatIds_thenReturnsReservations() {
        // given
        Long scheduleId = 1L;
        List<Long> seatIDs = List.of(1L, 2L, 3L);
        ReservationCommand.Get reservationGet = ReservationCommand.Get.of(scheduleId, seatIDs);

        List<Reservation> reservations = List.of(
                Reservation.of(scheduleId, 1L, 1L, LocalDateTime.of(2025, Month.JANUARY, 1, 12, 0)),
                Reservation.of(scheduleId, 2L, 1L, LocalDateTime.of(2025, Month.JANUARY, 1, 12, 0)),
                Reservation.of(scheduleId, 3L, 1L, LocalDateTime.of(2025, Month.JANUARY, 1, 12, 0))
        );
        given(reservationRepository.getReservations(reservationGet)).willReturn(reservations);

        // when
        List<Reservation> searchedReservations = sut.getReservations(reservationGet);

        // then
        assertThat(searchedReservations)
                .isNotNull()
                .hasSize(3)
                .extracting("seatId")
                .containsExactly(1L, 2L, 3L);

    }

}