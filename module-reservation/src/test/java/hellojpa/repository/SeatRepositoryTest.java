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
import static org.mockito.Mockito.*;

class SeatRepositoryTest {

    private SeatRepository seatRepository;
    private Seat seat1;
    private Seat seat2;
    private List<Long> seatIds;

    @BeforeEach
    void setUp() {
        seatRepository = mock(SeatRepository.class);

        Movie movie1 = new Movie("Movie1", VideoRating.ALL, LocalDate.now(), "https://xxx", 120, Genre.DRAMA);

        Theater theater1 = new Theater("Theater1");

        Screening screening1 = new Screening(movie1, theater1, LocalTime.now());

        Users user1 = new Users("user1", 20);

        Reservation reservation1 = new Reservation(user1, screening1);

        seat1 = new Seat(1L, theater1, "A", 1);
        seat2 = new Seat(2L, theater1, "A", 2);

        seatIds = Arrays.asList(seat1.getId(), seat2.getId());
    }

    @Test
    void findByIdWithPessimisticLock() {

        // Given
        when(seatRepository.findByIdWithPessimisticLock(seatIds)).thenReturn(Arrays.asList(seat1, seat2));

        // When
        List<Seat> seats = seatRepository.findByIdWithPessimisticLock(seatIds);

        // Then
        assertThat(seats).hasSize(2);
        assertThat(seats.get(0).getId()).isEqualTo(1L);
        assertThat(seats.get(1).getId()).isEqualTo(2L);

        verify(seatRepository, times(1)).findByIdWithPessimisticLock(seatIds);
    }

    @Test
    void findByIdWithOptimisticLock() {

        // Given
        when(seatRepository.findByIdWithOptimisticLock(seatIds)).thenReturn(Arrays.asList(seat1, seat2));

        // When
        List<Seat> seats = seatRepository.findByIdWithOptimisticLock(seatIds);

        // Then
        assertThat(seats).hasSize(2);
        assertThat(seats.get(0).getId()).isEqualTo(1L);
        assertThat(seats.get(1).getId()).isEqualTo(2L);

        verify(seatRepository, times(1)).findByIdWithOptimisticLock(seatIds);
    }
}
