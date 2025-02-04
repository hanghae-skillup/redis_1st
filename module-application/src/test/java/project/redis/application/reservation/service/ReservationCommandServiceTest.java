package project.redis.application.reservation.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.redis.application.reservation.port.inbound.ReserveCommandParam;
import project.redis.application.reservation.port.outbound.ReservationQueryPort;
import project.redis.application.seat.port.outbound.SeatQueryPort;
import project.redis.common.exception.DataInvalidException;
import project.redis.domain.cinema.Cinema;
import project.redis.domain.reservation.Reservation;
import project.redis.domain.seat.Seat;

@ExtendWith(MockitoExtension.class)
class ReservationCommandServiceTest {

    @Mock
    SeatQueryPort seatQueryPort;

    @Mock
    ReservationQueryPort reservationQueryPort;

    @InjectMocks
    ReservationCommandService reservationCommandService;

    @Test
    void testSeriesSeatNoSeriesSeat() {
        // Arrange
        UUID cinemaId = UUID.randomUUID();
        Cinema cinema = Cinema.generateCinema(cinemaId, "Test Cinema");

        Seat seat1 = Seat.generateSeat(UUID.randomUUID(), "A1", cinema);
        Seat seat2 = Seat.generateSeat(UUID.randomUUID(), "A2", cinema);
        Seat seat3 = Seat.generateSeat(UUID.randomUUID(), "A4", cinema);

        List<Seat> seats = List.of(seat1, seat2, seat3);

        List<UUID> seatIds = seats.stream().map(Seat::getSeatId).toList();
        ReserveCommandParam param = new ReserveCommandParam(seatIds,
                UUID.randomUUID(), "user");

        when(seatQueryPort.getSeats(seatIds)).thenReturn(seats);

        Assertions.assertThatThrownBy(() -> reservationCommandService.reserve(param));


    }

    @Test
    void testSeriesSeatInputModelNoValidate() {
        // Arrange
        UUID cinemaId = UUID.randomUUID();
        Cinema cinema = Cinema.generateCinema(cinemaId, "Test Cinema");

        Seat seat1 = Seat.generateSeat(UUID.randomUUID(), "A1", cinema);
        Seat seat2 = Seat.generateSeat(UUID.randomUUID(), "A2", cinema);
        Seat seat3 = Seat.generateSeat(UUID.randomUUID(), "A4", cinema);
        Seat seat4 = Seat.generateSeat(UUID.randomUUID(), "A4", cinema);
        Seat seat5 = Seat.generateSeat(UUID.randomUUID(), "A4", cinema);
        Seat seat6 = Seat.generateSeat(UUID.randomUUID(), "A4", cinema);

        List<Seat> seats = List.of(seat1, seat2, seat3, seat4, seat5, seat6);

        List<UUID> seatIds = seats.stream().map(Seat::getSeatId).toList();

        assertThrows(ConstraintViolationException.class, () -> new ReserveCommandParam(seatIds, UUID.randomUUID(), "user"));
    }

    @Test
    void testAlreadyReservedSeat() {
        // given
        UUID cinemaId = UUID.randomUUID();
        Cinema cinema = Cinema.generateCinema(cinemaId, "Test Cinema");
        UUID seat4Id = UUID.randomUUID();
        UUID seat2Id = UUID.randomUUID();

        Seat seat1 = Seat.generateSeat(UUID.randomUUID(), "A1", cinema);
        Seat seat2 = Seat.generateSeat(seat2Id, "A2", cinema);

        UUID screeningId = UUID.randomUUID();

        Seat seat3 = Seat.generateSeat(seat2Id, "A2", cinema);
        Seat seat4 = Seat.generateSeat(seat4Id, "A3", cinema);
        List<Seat> seats = List.of(seat3, seat4);

        List<UUID> seatIds = seats.stream().map(Seat::getSeatId).toList();
        ReserveCommandParam param = new ReserveCommandParam(seatIds,
                screeningId, "user");

        Reservation reservation = Reservation.generateReservation(
                null, null, null, null, List.of(seat1, seat2));

        when(seatQueryPort.getSeats(List.of(seat2Id, seat4Id))).thenReturn(List.of(seat3, seat4));
        when(reservationQueryPort.getReservations(screeningId)).thenReturn(List.of(reservation));


        assertThrows(DataInvalidException.class, () -> reservationCommandService.reserve(param));
    }



    @Test
    void testAlreadyReservationSeat5Exceed() {
        // given
        UUID cinemaId = UUID.randomUUID();
        UUID screeningId = UUID.randomUUID();
        Cinema cinema = Cinema.generateCinema(cinemaId, "Test Cinema");

        Seat seat1 = Seat.generateSeat(UUID.randomUUID(), "A1", cinema);
        Seat seat2 = Seat.generateSeat(UUID.randomUUID(), "A2", cinema);
        Seat seat3 = Seat.generateSeat(UUID.randomUUID(), "A3", cinema);
        Seat seat4 = Seat.generateSeat(UUID.randomUUID(), "A4", cinema);
        Seat seat5 = Seat.generateSeat(UUID.randomUUID(), "A5", cinema);

        List<Seat> alreadyReservedSeats = List.of(seat1, seat2, seat3, seat4, seat5);

        Seat seat6 = Seat.generateSeat(UUID.randomUUID(), "B5", cinema);

        ReserveCommandParam param = new ReserveCommandParam(List.of(seat6.getSeatId()),
                screeningId, "user");


        Reservation reservation = Reservation.generateReservation(
                null, null, null, null, alreadyReservedSeats);

        // when
        when(seatQueryPort.getSeats(List.of(seat6.getSeatId()))).thenReturn(List.of(seat6));
        when(reservationQueryPort.getReservations(screeningId)).thenReturn(List.of(reservation));


        assertThrows(DataInvalidException.class, () -> reservationCommandService.reserve(param));
    }

}