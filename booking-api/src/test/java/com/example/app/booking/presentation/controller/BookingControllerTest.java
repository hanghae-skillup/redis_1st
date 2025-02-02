package com.example.app.booking.presentation.controller;

import com.example.app.booking.presentation.config.EmbeddedRedisConfig;
import com.example.app.booking.presentation.dto.request.CreateBookingRequest;
import com.example.app.common.exception.APIException;
import com.example.app.common.exception.LockException;
import com.example.app.common.exception.RateLimitException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static com.example.app.booking.exception.BookingErrorMessage.SEAT_ROW_NOT_IN_SEQUENCE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = EmbeddedRedisConfig.class)
@TestPropertySource(properties = "spring.config.location = classpath:application-test.yml")
@Sql(scripts = "/booking-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class BookingControllerTest {

    @Autowired
    private BookingController bookingController;

    @Test
    public void 영화_예약_성공_테스트() throws InterruptedException {
        var bookingRequest = new CreateBookingRequest(1L, 2L, 5L, 1L, LocalDate.of(2025, 3, 1), List.of("A1", "A2"));
        var response = bookingController.createBooking(bookingRequest);
        assertEquals(201, response.getStatusCode().value());
    }

    @Test
    public void Rate_Limit_테스트() {
        var bookingRequest = new CreateBookingRequest(1L, 2L, 5L, 1L, LocalDate.of(2025, 3, 1), List.of("C1", "C2"));
        assertThrows(RateLimitException.class, () -> bookingController.createBooking(bookingRequest));
    }

    @Test
    public void 영화_예약_실패_테스트() {
        var bookingRequest = new CreateBookingRequest(2L, 2L, 5L, 1L, LocalDate.of(2025, 3, 1), List.of("A1", "B1"));
        var exception = assertThrows(APIException.class, () -> bookingController.createBooking(bookingRequest));
        assertEquals(exception.getMessage(), SEAT_ROW_NOT_IN_SEQUENCE.getMessage());
    }

    @Test
    public void 동시성_예약_테스트() throws InterruptedException {
        List<CreateBookingRequest> bookingRequests = new ArrayList<>();
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger exceptionCount = new AtomicInteger(0);


        for (int i=0; i < 10; i++) {
            bookingRequests.add(new CreateBookingRequest((long) i+5, 2L, 5L, 1L, LocalDate.of(2025, 3, 1), List.of("E1", "E2")));
        }

        int threadCount = bookingRequests.size();

        ExecutorService executor = Executors.newFixedThreadPool(threadCount); // pool 생성
        CountDownLatch latch = new CountDownLatch(threadCount); // 쓰레드 작업 카운트

        for (int i = 0; i < threadCount; i++) {
            final int taskId = i;

            executor.execute(() -> {
                try {
                    bookingController.createBooking(bookingRequests.get(taskId));
                    successCount.incrementAndGet();
                } catch (LockException | APIException e) {
                    exceptionCount.incrementAndGet();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await(); // 카운트 0까지 기다림
        executor.shutdown(); // pool 종료

        assertEquals(1, successCount.get());
        assertEquals(9, exceptionCount.get());
    }
}
