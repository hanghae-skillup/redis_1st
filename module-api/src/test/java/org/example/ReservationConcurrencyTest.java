package org.example;

import org.assertj.core.api.Assertions;
import org.example.dto.request.ReservationRequestDto;
import org.example.dto.request.ReservationSeatDto;
import org.example.repository.ReservationSeatRepository;
import org.example.service.reservation.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
@ActiveProfiles("test")
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
        reservationSeatDtos.add(new ReservationSeatDto("ROW_A", "COL_2"));

        List<ReservationSeatDto> reservationSeatDtos2 = new ArrayList<>();
        reservationSeatDtos2.add(new ReservationSeatDto("ROW_A", "COL_2"));
        reservationSeatDtos2.add(new ReservationSeatDto("ROW_A", "COL_3"));


        for (long i = 0; i < numberOfThreads; i++) {
            ReservationRequestDto reservationRequestDto = new ReservationRequestDto(i, 2L, reservationSeatDtos);
            ReservationRequestDto reservationRequestDto2 = new ReservationRequestDto(i+100, 2L, reservationSeatDtos2);

            executorService.execute(() -> {
                try {
                    reservationService.reserveMovie(reservationRequestDto);
                    reservationService.reserveMovie(reservationRequestDto2);
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
        Assertions.assertThat(reservedSeats.size()).isEqualTo(2);
    }
}
