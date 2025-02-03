package com.example.reservation.service;

import com.example.jpa.repository.movie.SeatRepository;
import com.example.jpa.repository.reservation.ReservationRepository;
import com.example.reservation.dto.ReservationRequest;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ReservationConcurrencyTest {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void testConcurrencyOnReserveSeat() throws InterruptedException {
        int threadCount = 100; // 동시에 실행할 쓰레드 수
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 1; i <= threadCount; i++) {
            final long userId = i; // userId를 final로 할당
            executorService.submit(() -> {
                try {
                    ReservationRequest request = new ReservationRequest(
                            userId, // 각 쓰레드마다 다른 userId 사용
                            1L,     // screeningId
                            Arrays.asList("A1", "A2") // 예약할 좌석
                    );
                    reservationService.reserveSeat(request);
                } catch (Exception e) {
                    // 예외 발생 시 로깅 또는 무시 (동시성 문제로 예외가 발생하는지 확인용)
                    System.err.println("예약 실패: " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await(); // 모든 쓰레드가 작업을 완료할 때까지 대기


        // 예약된 수가 최대 한 번만 발생했는지 확인
        long reservationCount = reservationRepository.count();
        assertEquals(2 , reservationCount); // 동시에 실행했어도 하나의 예약만 성공해야 함
    }

}
