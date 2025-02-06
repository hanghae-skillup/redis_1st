package org.example;

import org.assertj.core.api.Assertions;
import org.example.dto.request.ReservationRequestDto;
import org.example.dto.request.ReservationSeatDto;
import org.example.repository.ReservationSeatRepository;
import org.example.service.reservation.ReservationService;
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
    private ReservationSeatRepository reservationSeatRepository;

    @Test
    void testConcurrentReservation() throws InterruptedException {
        int numberOfThreads = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        List<ReservationSeatDto> reservationSeatDtos = new ArrayList<>();
        reservationSeatDtos.add(new ReservationSeatDto("ROW_A", "COL_1"));

        for (long i = 0; i < numberOfThreads; i++) {
            ReservationRequestDto reservationRequestDto = new ReservationRequestDto(i, 2L, reservationSeatDtos);
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

        List<Long> reservedSeats = reservationSeatRepository.findReservedSeatByScreenScheduleId(2L);
        Assertions.assertThat(reservedSeats.size()).isEqualTo(1);
    }
}
