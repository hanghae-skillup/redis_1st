package org.example;

import org.assertj.core.api.Assertions;
import org.example.dto.request.ReservationRequestDto;
import org.example.dto.request.ReservationSeat;
import org.example.repository.ReservationJpaRepository;
import org.example.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
@Transactional
public class ReservationConcurrencyTest {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationJpaRepository reservationJpaRepository;

    @Test
    void testConcurrentReservation() throws InterruptedException {
        int numberOfThreads = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        List<ReservationSeat> reservationSeats = new ArrayList<>();
        reservationSeats.add(new ReservationSeat("ROW_A", "COL_1"));

        for (long i = 0; i < numberOfThreads; i++) {
            ReservationRequestDto reservationRequestDto = new ReservationRequestDto(i, 2L, reservationSeats);
            executorService.execute(() -> {
                try {
                    reservationService.reserveMovie(reservationRequestDto);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        List<Long> reservedSeatByUserId = reservationJpaRepository.findReservedSeatByScreenScheduleId(2L);
        Assertions.assertThat(reservedSeatByUserId.size()).isEqualTo(1);
    }
}
