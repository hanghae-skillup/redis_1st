package com.example.app.booking.domain;

import com.example.app.common.exception.APIException;
import com.example.app.config.EmbeddedRedisConfig;
import com.example.app.movie.type.TheaterSeat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static com.example.app.booking.exception.BookingErrorMessage.SEAT_ALREADY_OCCUPIED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = EmbeddedRedisConfig.class)
@TestPropertySource(properties = "spring.config.location = classpath:application-test.yml")
public class SeatTest {

    @Test
    public void 예약_가능_자리_여부_테스트() {
        var hasReservedSeats = List.of(
                Seat.builder().theaterSeat(TheaterSeat.A1).reserved(false).build(),
                Seat.builder().theaterSeat(TheaterSeat.A2).reserved(true).build(),
                Seat.builder().theaterSeat(TheaterSeat.A3).reserved(false).build());

        var exception = assertThrows(APIException.class, () -> Seat.checkSeatsAvailable(hasReservedSeats));
        assertEquals(SEAT_ALREADY_OCCUPIED.getMessage(), exception.getMessage());
    }
}
