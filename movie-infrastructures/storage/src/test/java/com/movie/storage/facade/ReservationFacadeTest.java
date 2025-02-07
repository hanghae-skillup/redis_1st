package com.movie.storage.facade;

import com.movie.application.movie.facade.ReservationFacade;
import com.movie.domain.movie.dto.command.ReservationCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ReservationFacadeTest {

    @Autowired
    private ReservationFacade reservationFacade;

    @DisplayName("동시성이 발생되는 ")
    @Test
    void test() throws InterruptedException {
        // given
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        Long scheduleId = 1L; // 스케줄 ID
        List<Long> seatIds = List.of(1L, 2L, 3L); // 예약 좌석 ID

        AtomicInteger count = new AtomicInteger(0);
        List<String> tokens = List.of(
                "734488355d85", "6f8f504681f9", "b02567dca468",
                "66b40f8df234", "2ff449c014be", "4fb8cf64e7fc",
                "9b8c1b3b43d6", "053ce98d889e", "8c7ea614a541", "1f504eb92b17"
        );

        // when
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    String token = tokens.get(ThreadLocalRandom.current().nextInt(tokens.size()));
                    reservationFacade.makeReservationsByFunctionalLock(ReservationCommand.GetReserveData.of(scheduleId, seatIds, token));
                    count.getAndIncrement();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        // then  - 자리예약은 한번만 성공하도록 함
        assertThat(count.get()).isEqualTo(1);
    }

}