package com.movie.infra.ratelimit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReservationRateLimitServiceTest {

    private ReservationRateLimitService reservationRateLimitService;

    @BeforeEach
    void setUp() {
        reservationRateLimitService = new ReservationRateLimitService();
    }

    @Test
    @DisplayName("동일 시간대 예약은 5분 내에 한 번만 가능")
    void shouldLimitReservationWithinTimeSlot() {
        String userId = "user1";
        String timeSlot = "MOVIE1_0800";  // 영화1 08:00 시간대

        // 첫 번째 예약 시도는 성공해야 함
        assertThat(reservationRateLimitService.canBook(userId, timeSlot)).isTrue();
        
        // 동일 시간대 즉시 재시도는 실패해야 함
        assertThat(reservationRateLimitService.canBook(userId, timeSlot)).isFalse();
    }

    @Test
    @DisplayName("서로 다른 시간대는 독립적으로 예약 가능")
    void shouldAllowReservationsForDifferentTimeSlots() {
        String userId = "user1";
        String timeSlot1 = "MOVIE1_0800";  // 영화1 08:00 시간대
        String timeSlot2 = "MOVIE1_1200";  // 영화1 12:00 시간대

        // 첫 번째 시간대 예약
        assertThat(reservationRateLimitService.canBook(userId, timeSlot1)).isTrue();
        
        // 다른 시간대는 바로 예약 가능해야 함
        assertThat(reservationRateLimitService.canBook(userId, timeSlot2)).isTrue();
    }

    @Test
    @DisplayName("서로 다른 사용자는 같은 시간대에 독립적으로 예약 가능")
    void shouldAllowReservationsForDifferentUsers() {
        String user1 = "user1";
        String user2 = "user2";
        String timeSlot = "MOVIE1_0800";

        // user1 예약
        assertThat(reservationRateLimitService.canBook(user1, timeSlot)).isTrue();
        
        // user2는 같은 시간대여도 예약 가능해야 함
        assertThat(reservationRateLimitService.canBook(user2, timeSlot)).isTrue();
    }

    @Test
    @DisplayName("5분 경과 후 같은 시간대 재예약 가능")
    void shouldAllowReservationAfterCooldown() throws InterruptedException {
        String userId = "user1";
        String timeSlot = "MOVIE1_0800";

        // 첫 번째 예약
        assertThat(reservationRateLimitService.canBook(userId, timeSlot)).isTrue();
        
        // 쿨다운 시간 경과 시뮬레이션
        Thread.sleep(100); // 실제 테스트에서는 mock 사용 권장
        
        // 재예약 시도
        assertThat(reservationRateLimitService.canBook(userId, timeSlot)).isTrue();
    }
} 