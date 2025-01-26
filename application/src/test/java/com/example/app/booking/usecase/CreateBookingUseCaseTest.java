package com.example.app.booking.usecase;

import com.example.app.booking.dto.CreateBookingCommand;
import com.example.app.booking.dto.SearchBookingCommand;
import com.example.app.booking.out.persistence.adapter.BookingPersistenceAdapter;
import com.example.app.booking.out.persistence.adapter.SeatPersistenceAdapter;
import com.example.app.config.QuerydslConfig;
import com.example.app.movie.type.TheaterSeat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = "spring.config.location = classpath:application-test.yml")
@Import({QuerydslConfig.class, SeatPersistenceAdapter.class, BookingPersistenceAdapter.class})
@Sql(scripts = "/seat-data.sql")
public class CreateBookingUseCaseTest {

    @Autowired
    private CreateBookingUseCase createBookingUseCase;

    @Autowired
    private BookingPersistenceAdapter bookingPersistenceAdapter;

    private final List<CreateBookingCommand> users = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        var user1Command = CreateBookingCommand.builder()
                .userId(1L)
                .movieId(2L)
                .showtimeId(5L)
                .theaterId(1L)
                .bookingDate(LocalDate.of(2025, 3, 1))
                .seats(Set.of(TheaterSeat.A3, TheaterSeat.A4, TheaterSeat.A5))
                .build();

        var user2Command = CreateBookingCommand.builder()
                .userId(2L)
                .movieId(2L)
                .showtimeId(5L)
                .theaterId(1L)
                .bookingDate(LocalDate.of(2025, 3, 1))
                .seats(Set.of(TheaterSeat.A3, TheaterSeat.A4, TheaterSeat.A5))
                .build();

        var user3Command = CreateBookingCommand.builder()
                .userId(3L)
                .movieId(2L)
                .showtimeId(5L)
                .theaterId(1L)
                .bookingDate(LocalDate.of(2025, 3, 1))
                .seats(Set.of(TheaterSeat.A3, TheaterSeat.A4, TheaterSeat.A5))
                .build();

        users.add(user1Command);
        users.add(user2Command);
        users.add(user3Command);
    }

    @Test
    public void 동시성_예약_테스트() throws InterruptedException {
        int threadCount = users.size();

        ExecutorService executor = Executors.newFixedThreadPool(threadCount); // pool 생성
        CountDownLatch latch = new CountDownLatch(threadCount); // 쓰레드 작업 카운트

        for (int i = 0; i < threadCount; i++) {
            final int taskId = i;
            executor.execute(() -> {
                try {
                    createBookingUseCase.createBooking(users.get(taskId));
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await(); // 카운트 0까지 기다림
        executor.shutdown(); // pool 종료

        var command = SearchBookingCommand.builder()
                .movieId(2L)
                .showtimeId(5L)
                .theaterId(1L)
                .bookingDate(LocalDate.of(2025, 3, 1))
                .build();

        var bookings = bookingPersistenceAdapter.loadAllBookings(command);

        assertEquals(1, bookings.size(), "예약은 하나만 성공");
    }
}
