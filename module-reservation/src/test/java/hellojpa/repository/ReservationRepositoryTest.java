package hellojpa.repository;

import hellojpa.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class ReservationRepositoryTest {

    private ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);
    private Screening screening;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        // 가짜 데이터 생성
        Movie movie1 = new Movie("Movie1", VideoRating.ALL, LocalDate.now(), "https://xxx", 120, Genre.DRAMA);

        Theater theater1 = new Theater("Theater1");

        screening = new Screening(movie1, theater1, LocalTime.now());

        Users user1 = new Users("user1", 20);

        reservation = new Reservation(user1, screening);

        Seat seat1 = new Seat(theater1, "A", 1, reservation);
        Seat seat2 = new Seat(theater1, "A", 2, reservation);

        // 가짜 Repository 동작 설정
        when(reservationRepository.findReservedSeatsByScreeningId(screening.getId()))
                .thenReturn(Arrays.asList(seat1, seat2));
    }

    @Test
    void findReservedSeatsByScreeningId() {
        // When
        List<Seat> reservedSeats = reservationRepository.findReservedSeatsByScreeningId(screening.getId());

        // Then
        assertThat(reservedSeats).isNotNull();
        assertThat(reservedSeats).hasSize(2);
        assertThat(reservedSeats.get(0).getReservation()).isEqualTo(reservation);
    }
}
