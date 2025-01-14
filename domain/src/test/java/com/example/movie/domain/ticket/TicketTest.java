package com.example.movie.domain.ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.example.movie.domain.schedule.Schedule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class TicketTest {

    @Nested
    @DisplayName("티켓 생성")
    class ConstructorTest {

        @Test
        @DisplayName("티켓 생성 성공")
        void success_0() {
            // given
            Schedule schedule = mock(Schedule.class);
            SeatsColType seatsCol = SeatsColType.A;
            SeatsRowType seatsRow = SeatsRowType.R_1;

            // when
            Ticket ticket = new Ticket(schedule, seatsCol, seatsRow);

            // then
            assertNotNull(ticket);
            assertEquals(schedule, ticket.schedule());
            assertEquals(seatsCol, ticket.seatsCol());
            assertEquals(seatsRow, ticket.seatsRow());
        }

        @NullSource
        @ParameterizedTest
        @DisplayName("실패 - 스케줄이 null")
        void fail_0(Schedule wrongSchedule) {
            // given
            SeatsColType seatsCol = SeatsColType.A;
            SeatsRowType seatsRow = SeatsRowType.R_1;

            // when then
            assertThrows(NullPointerException.class,
                () -> new Ticket(wrongSchedule, seatsCol, seatsRow));
        }

        @NullSource
        @ParameterizedTest
        @DisplayName("실패 - 좌석 열이 null")
        void fail_1(SeatsColType wrongSeatsCol) {
            // given
            Schedule schedule = mock(Schedule.class);
            SeatsRowType seatsRow = SeatsRowType.R_1;

            // when then
            assertThrows(NullPointerException.class,
                () -> new Ticket(schedule, wrongSeatsCol, seatsRow));
        }

        @NullSource
        @ParameterizedTest
        @DisplayName("실패 - 좌석 행이 null")
        void fail_2(SeatsRowType wrongSeatsRow) {
            // given
            Schedule schedule = mock(Schedule.class);
            SeatsColType seatsCol = SeatsColType.A;

            // when then
            assertThrows(NullPointerException.class,
                () -> new Ticket(schedule, seatsCol, wrongSeatsRow));
        }
    }
}