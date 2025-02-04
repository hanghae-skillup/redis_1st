package com.example.app.booking.service;

import com.example.app.booking.domain.Booking;
import com.example.app.booking.dto.CreateBookingCommand;
import com.example.app.booking.out.persistence.adapter.BookingPersistenceAdapter;
import com.example.app.booking.out.persistence.adapter.SeatPersistenceAdapter;
import com.example.app.common.exception.APIException;
import com.example.app.common.function.DistributedLockService;
import com.example.app.config.EmbeddedRedisConfig;
import com.example.app.movie.type.TheaterSeat;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.Set;
import java.util.function.Supplier;

import static com.example.app.booking.exception.BookingErrorMessage.SEAT_ROW_NOT_IN_SEQUENCE;
import static com.navercorp.fixturemonkey.api.instantiator.Instantiator.constructor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = EmbeddedRedisConfig.class)
@TestPropertySource(properties = "spring.config.location = classpath:application-test.yml")
public class CreateBookingServiceTest {

    private FixtureMonkey fixtureMonkey;

    @Mock
    private DistributedLockService distributedLockService;

    @Mock
    private BookingPersistenceAdapter bookingPersistenceAdapter;

    @Mock
    private SeatPersistenceAdapter seatPersistenceAdapter;

    @InjectMocks
    private CreateBookingService sut;

    @BeforeEach
    void setUp() {
        fixtureMonkey = FixtureMonkey.builder()
                .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
                .plugin(new JakartaValidationPlugin())
                .build();
    }

    @Test
    public void 예약_테스트() {
        var key = fixtureMonkey.giveMeOne(String.class);
        var continuousSeats = Set.of(TheaterSeat.A3, TheaterSeat.A4, TheaterSeat.A5);
        var bookingCommand = fixtureMonkey.giveMeBuilder(CreateBookingCommand.class)
                .instantiate(constructor()
                        .parameter(long.class)
                        .parameter(long.class)
                        .parameter(long.class)
                        .parameter(long.class)
                        .parameter(LocalDate.class)
                        .parameter(Set.class, "seats"))
                .set("seats", continuousSeats)
                .sample();

        var booking = fixtureMonkey.giveMeBuilder(Booking.class)
                .instantiate(constructor()
                        .parameter(long.class)
                        .parameter(long.class)
                        .parameter(long.class)
                        .parameter(long.class)
                        .parameter(long.class)
                        .parameter(int.class, "totalSeats")
                        .parameter(LocalDate.class))
                .set("totalSeats", 3)
                .sample();

        when(distributedLockService.executeWithLockAndReturn(any(Supplier.class), any(String.class), any(Long.class), any(Long.class)))
                .thenReturn(booking);

        var result = sut.createBooking(key, bookingCommand);

        assertEquals(booking, result);
    }

    @Test
    public void 예약_불가_테스트() {
        var key = fixtureMonkey.giveMeOne(String.class);
        var discontinuousSeats = Set.of(TheaterSeat.B1, TheaterSeat.C1, TheaterSeat.D1);
        var bookingCommand = fixtureMonkey.giveMeBuilder(CreateBookingCommand.class)
                .instantiate(constructor()
                        .parameter(long.class)
                        .parameter(long.class)
                        .parameter(long.class)
                        .parameter(long.class)
                        .parameter(LocalDate.class)
                        .parameter(Set.class, "seats"))
                .set("seats", discontinuousSeats)
                .sample();

        var exception = assertThrows(APIException.class, () -> sut.createBooking(key, bookingCommand));
        assertEquals(exception.getMessage(), SEAT_ROW_NOT_IN_SEQUENCE.getMessage());
    }
}
