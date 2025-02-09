package com.movie.storage.movie.repository;

import com.movie.domain.movie.domain.Reservation;
import com.movie.domain.movie.dto.command.ReservationCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@SpringBootTest
class ReservationRepositoryImplTest {

    @Autowired
    private ReservationRepositoryImpl sut;

    @DisplayName("스케줄 id, 좌석 ids를 이용하여, 예약 목록을 조회한다.")
    @Test
    @Transactional
    void givenScheduleIdAndSeatIds_whenSearchingReservations_thenReturnReservations() {
        // given
        Long scheduleId = 1L;
        List<Long> seatIds = List.of(1L, 2L, 3L);
        ReservationCommand.Get get = ReservationCommand.Get.of(scheduleId, seatIds);

        // when
        List<Reservation> reservations = sut.getReservations(get);

        // then
        assertThat(reservations)
                .isNotEmpty()
                .hasSize(3)
                .extracting("scheduleId", "seatId")
                .containsExactly(
                        tuple(scheduleId, 1L),
                        tuple(scheduleId, 2L),
                        tuple(scheduleId, 3L)
                );
    }

    @DisplayName("스케줄 id, 좌석 ids, 사용자 id를 이용하여, 좌석을 예매한다.")
    @Test
    void givenScheduleIdAndSeatIdsAndUserId_whenMakingReservations_thenMakeReservations() {
        // given
        Long scheduleId = 1L;
        List<Long> seatIds = List.of(1L, 2L, 3L);
        Long userId = 1L;
        ReservationCommand.Reserve reserve = ReservationCommand.Reserve.of(scheduleId, seatIds, userId);

        // when
        List<Reservation> reservations = sut.makeReservation(reserve);

        // then
        assertThat(reservations)
                .isNotEmpty()
                .hasSize(3)
                .extracting("scheduleId", "seatId", "userId")
                .containsExactly(
                        tuple(scheduleId, 1L, userId),
                        tuple(scheduleId, 2L, userId),
                        tuple(scheduleId, 3L, userId)
                );
    }

}