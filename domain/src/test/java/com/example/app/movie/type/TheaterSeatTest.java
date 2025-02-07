package com.example.app.movie.type;

import com.example.app.common.exception.APIException;
import com.example.app.config.EmbeddedRedisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Set;

import static com.example.app.booking.exception.BookingErrorMessage.SEAT_ROW_NOT_IN_SEQUENCE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = EmbeddedRedisConfig.class)
@TestPropertySource(properties = "spring.config.location = classpath:application-test.yml")
public class TheaterSeatTest {

    @Test
    public void 열_조회_테스트() {
        assertEquals("A", TheaterSeat.A1.getRow());
        assertEquals("B", TheaterSeat.B1.getRow());
        assertEquals("C", TheaterSeat.C1.getRow());
        assertEquals("D", TheaterSeat.D1.getRow());
        assertEquals("E", TheaterSeat.E1.getRow());
    }

    @Test
    public void 연속된_열_체크_테스트() {
        var discontinuousSeats = Set.of(TheaterSeat.B1, TheaterSeat.C1, TheaterSeat.D1);
        var exception = assertThrows(APIException.class, () -> TheaterSeat.checkSeatsInSequence(discontinuousSeats));
        assertEquals(SEAT_ROW_NOT_IN_SEQUENCE.getMessage(), exception.getMessage());
    }
}
