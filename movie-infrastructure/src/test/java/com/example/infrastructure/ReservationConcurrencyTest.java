package com.example.infrastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.application.dto.request.ReservationRequestDto;
import com.example.application.port.in.ReservationServicePort;
import com.example.application.port.out.ReservationRepositoryPort;
import com.example.application.port.out.ScreeningSeatRepositoryPort;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReservationConcurrencyTest {

    @Autowired
    private ReservationServicePort reservationServicePort;

    @Autowired
    private ReservationRepositoryPort reservationRepositoryPort;

    @Autowired
    private ScreeningSeatRepositoryPort screeningSeatRepositoryPort;

    private final Long screeningId = 1L;
    private final List<Long> seatIds = List.of(1L, 2L, 3L);

    @BeforeEach
    void setUp() {
        // 기존 예약 데이터 초기화
        reservationRepositoryPort.deleteAll();
        screeningSeatRepositoryPort.resetAllReservations();
    }

    @DisplayName("100개 스레드가 동일한 좌석을 동시에 예약할 때 중복 예약이 발생하지 않는다.")
    @Test
    void shouldPreventConcurrentSeatReservation() throws InterruptedException {
        // given: 100개의 동시 예약 요청 설정
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        Runnable reservationTask = () -> {
            try {
                Long memberId = (Thread.currentThread().threadId() % 100) + 1;
                ReservationRequestDto request = new ReservationRequestDto(screeningId, memberId, seatIds);
                reservationServicePort.create(request);
            } catch (Exception e) {
                System.err.println("❌ 예약 실패: " + e.getMessage());
            } finally {
                latch.countDown();
            }
        };

        // when: 100개의 스레드가 동시에 좌석 예약 요청을 보냄
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(reservationTask);
        }

        latch.await(); // 모든 스레드가 완료될 때까지 대기
        executorService.shutdown();

        // then: 실제로 예약된 좌석 개수 확인
        long reservedSeatsCount = screeningSeatRepositoryPort.countReservedSeats(screeningId);
        System.out.println("✅ 최종 예약된 좌석 개수: " + reservedSeatsCount);

        assertEquals(seatIds.size(), reservedSeatsCount, "동시성 이슈 발생으로 좌석이 중복 예약됨!");
    }

}
