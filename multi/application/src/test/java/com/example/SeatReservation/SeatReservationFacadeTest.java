package com.example.presentation.SeatReservation;

import com.example.application.SeatReservation.SeatReservationFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SeatReservationFacadeTest {

    @Autowired
    private SeatReservationFacade seatReservationFacade;
    @Test
    void testConcurrentReservations() throws InterruptedException {
        // 테스트 데이터
        Long screeningId = 1L;
        String seatNumber = "A1";
        int threadCount = 10;

        // 동시성 테스트 설정
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        int[] successCount = {0}; // 성공한 요청 카운트

        for (int i = 0; i < threadCount; i++) {
            executorService.execute(() -> {
                Long randomUserId = ThreadLocalRandom.current().nextLong(1, 100);
                try {
                    boolean success = seatReservationFacade.reserve(screeningId, randomUserId, seatNumber, 1);
                    if (success) {
                        synchronized (successCount) {
                            successCount[0]++;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await(); // 모든 스레드 완료 대기
        executorService.shutdown();

        // 성공한 예약은 1개여야 함
        assertThat(successCount[0]).isEqualTo(1);
    }
}