package project.redis.domain.seat;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import project.redis.domain.cinema.Cinema;

class SeatTest {

    @Test
    void testIsSeries() {
        UUID cinemaId = UUID.randomUUID();
        Cinema cinema = Cinema.generateCinema(cinemaId, "Test Cinema");

        Seat seat1 = Seat.generateSeat(UUID.randomUUID(), "A1", cinema);
        Seat seat2 = Seat.generateSeat(UUID.randomUUID(), "A2", cinema);
        Seat seat3 = Seat.generateSeat(UUID.randomUUID(), "A3", cinema);

        List<Seat> seats = List.of(seat1, seat2, seat3);

        boolean result = Seat.isSeries(seats);

        assertThat(result).isTrue();
    }

    @Test
    void testIsSeriesNoEqualsColumn() {
        UUID cinemaId = UUID.randomUUID();
        Cinema cinema = Cinema.generateCinema(cinemaId, "Test Cinema");

        Seat seat1 = Seat.generateSeat(UUID.randomUUID(), "A1", cinema);
        Seat seat2 = Seat.generateSeat(UUID.randomUUID(), "A2", cinema);
        Seat seat3 = Seat.generateSeat(UUID.randomUUID(), "B3", cinema);

        List<Seat> seats = List.of(seat1, seat2, seat3);

        boolean result = Seat.isSeries(seats);

        assertThat(result).isFalse();
    }


    @Test
    void testIsSeriesNoSeriesRow() {
        UUID cinemaId = UUID.randomUUID();
        Cinema cinema = Cinema.generateCinema(cinemaId, "Test Cinema");

        Seat seat1 = Seat.generateSeat(UUID.randomUUID(), "A1", cinema);
        Seat seat2 = Seat.generateSeat(UUID.randomUUID(), "A2", cinema);
        Seat seat3 = Seat.generateSeat(UUID.randomUUID(), "A4", cinema);

        List<Seat> seats = List.of(seat1, seat2, seat3);

        boolean result = Seat.isSeries(seats);

        assertThat(result).isFalse();
    }
}