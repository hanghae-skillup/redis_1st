package com.movie.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ReservationConcurrencyTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("동시에 같은 좌석 예매 시도 시 하나만 성공해야 함")
    void concurrentReservationTest() throws Exception {
        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        List<Future<ResponseEntity<String>>> futures = new ArrayList<>();

        // 동시에 10개의 요청 실행
        for (int i = 0; i < numberOfThreads; i++) {
            long userId = (i % 2) + 1; // 두 명의 사용자가 동시에 예약 시도
            futures.add(executorService.submit(() -> {
                try {
                    return restTemplate.postForEntity(
                            "http://localhost:" + port + "/api/v1/reservations?userId=" + userId + "&scheduleId=1&seatId=1",
                            null,
                            String.class
                    );
                } finally {
                    latch.countDown();
                }
            }));
        }

        // 모든 요청이 완료될 때까지 대기
        latch.await();
        executorService.shutdown();

        // 결과 검증
        int successCount = 0;
        for (Future<ResponseEntity<String>> future : futures) {
            ResponseEntity<String> response = future.get();
            if (response.getStatusCode().is2xxSuccessful()) {
                successCount++;
            }
        }

        // 하나의 요청만 성공해야 함
        assertThat(successCount).isEqualTo(1);
    }
} 