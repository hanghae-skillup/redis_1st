package com.example.movie.domain.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.movie.domain.movie.Movie;
import com.example.movie.domain.movie.MovieTest;
import java.time.LocalTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class ScheduleTest {

    @Nested
    @DisplayName("상영 시간표 생성")
    class ConstructorTest {

        @Test
        @DisplayName("상영 시간표 생성 성공")
        void success_0() {
            // given
            Movie movie = MovieTest.makeMovie();
            LocalTime startAt = LocalTime.of(10, 0);
            LocalTime endAt = LocalTime.of(12, 0);

            // when
            Schedule schedule = new Schedule(movie, startAt, endAt);

            // then
            assertNotNull(schedule);
            assertEquals(movie, schedule.movie());
            assertEquals(startAt, schedule.startAt());
            assertEquals(endAt, schedule.endAt());
            assertEquals(0, schedule.tickets().size());
        }

        @NullSource
        @ParameterizedTest
        @DisplayName("실패 - 영화가 null")
        void fail_0(Movie wrongMovie) {
            // given when then
            assertThrows(NullPointerException.class,
                () -> new Schedule(wrongMovie, LocalTime.of(10, 0), LocalTime.of(12, 0)));
        }

        @NullSource
        @ParameterizedTest
        @DisplayName("실패 - 시작 시간이 null")
        void fail_1(LocalTime wrongStartAt) {
            // given when then
            assertThrows(NullPointerException.class,
                () -> new Schedule(MovieTest.makeMovie(), wrongStartAt, LocalTime.of(12, 0)));
        }

        @NullSource
        @ParameterizedTest
        @DisplayName("실패 - 종료 시간이 null")
        void fail_2(LocalTime wrongEndAt) {
            // given when then
            assertThrows(NullPointerException.class,
                () -> new Schedule(MovieTest.makeMovie(), LocalTime.of(10, 0), wrongEndAt));
        }

        @Test
        @DisplayName("실패 - 시작 시간이 종료 시간 이후")
        void fail_3() {
            // given
            LocalTime startAt = LocalTime.of(12, 0);
            LocalTime endAt = startAt.minusHours(1);

            // when then
            assertThrows(IllegalArgumentException.class,
                () -> new Schedule(MovieTest.makeMovie(), startAt, endAt));
        }

        @Test
        @DisplayName("실패 - 시작 시간이 종료 시간과 같음")
        void fail_4() {
            // given
            LocalTime startAt = LocalTime.of(10, 0);
            LocalTime endAt = LocalTime.of(10, 0);

            // when then
            assertThrows(IllegalArgumentException.class,
                () -> new Schedule(MovieTest.makeMovie(), startAt, endAt));
        }
    }
}
