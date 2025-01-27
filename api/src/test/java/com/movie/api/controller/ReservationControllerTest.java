package com.movie.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ReservationControllerTest {

    private final TestRestTemplate restTemplate = new TestRestTemplate();
    private final String baseUrl = "http://localhost:8080";

    @Test
    void concurrentReservationTest() throws Exception {
        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            long userId = (i % 2) + 1; // 두 명의 사용자가 동시에 예약 시도
            executorService.submit(() -> {
                try {
                    String url = baseUrl + "/api/v1/reservations?userId=" + userId + "&scheduleId=1&seatId=1";
                    ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
                    System.out.println("Response: " + response.getBody());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();
    }
} 