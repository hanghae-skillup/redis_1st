package hellojpa.service;

import hellojpa.domain.*;
import hellojpa.dto.ReservationCompletedMessageDto;
import hellojpa.dto.ReservationRequestDto;
import hellojpa.exception.SeatReservationException;
import hellojpa.publisher.EventPublisher;
import hellojpa.repository.ReservationRepository;
import hellojpa.repository.ScreeningRepository;
import hellojpa.repository.SeatRepository;
import hellojpa.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationTransactionalServiceTest {

    @InjectMocks
    private ReservationTransactionalService reservationService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ScreeningRepository screeningRepository;

    @Mock
    private SeatRepository seatRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private EventPublisher eventPublisher;

    private ReservationRequestDto requestDto, requestDtoAgeException;
    private Users user1, user2;
    private Screening screening1, screening2;
    private Movie movie1, movie2;
    private Seat seat1, seat2;
    private Theater theater;

    @BeforeEach
    void setUp() {
        user1 = new Users(1L, "user1", 20);  // 20세 사용자
        user2 = new Users(2L, "user2", 17); // 17세 사용자
        movie1 = new Movie(1L, "Test Movie1", VideoRating.ALL, LocalDate.now(), "https://xxx", 120, Genre.DRAMA);
        movie2 = new Movie(1L, "Test Movie2", VideoRating.AGE_19, LocalDate.now(), "https://xxx", 120, Genre.DRAMA);
        theater = new Theater(1L, "Theater1");
        screening1 = new Screening(1L, movie1, theater, LocalTime.now());
        screening2 = new Screening(2L, movie2, theater, LocalTime.now());

        seat1 = new Seat(1L, theater, "A", 1);
        seat2 = new Seat(2L, theater,  "A", 2);

        requestDto = new ReservationRequestDto(1L, 1L, List.of(1L, 2L));
        requestDtoAgeException = new ReservationRequestDto(2L, 2L, List.of(1L, 2L));
    }

    @Test
    void testSuccessfulReservation() {
        // Given
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(screeningRepository.findById(1L)).thenReturn(Optional.of(screening1));
        when(seatRepository.findByIdWithOptimisticLock(requestDto.getReservationSeatsId()))
                .thenReturn(List.of(seat1, seat2));
        when(reservationRepository.findReservedSeatsByScreeningId(1L)).thenReturn(List.of());

        // When
        assertDoesNotThrow(() -> reservationService.reservationProcess(requestDto));

        // Then
        verify(reservationRepository, times(1)).save(any(Reservation.class));
        verify(eventPublisher, times(1)).publish(any(ReservationCompletedMessageDto.class));
    }

    @Test
    void testUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> reservationService.reservationProcess(requestDto));

        assertEquals("존재하지 않는 사용자입니다.", exception.getMessage());
    }

    @Test
    void testScreeningNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(screeningRepository.findById(1L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> reservationService.reservationProcess(requestDto));

        assertEquals("존재하지 않는 상영 정보입니다.", exception.getMessage());
    }

    @Test
    void testAgeRestriction() {
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));
        when(screeningRepository.findById(2L)).thenReturn(Optional.of(screening2));

        SeatReservationException exception = assertThrows(SeatReservationException.class,
                () -> reservationService.reservationProcess(requestDtoAgeException));

        assertEquals("해당 영화는 나이 제한으로 예매할 수 없습니다.", exception.getMessage());
    }

    @Test
    void testTooManySeatsReserved() {
        requestDto = new ReservationRequestDto(1L, 1L, List.of(1L, 2L, 3L, 4L, 5L, 6L));

        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(screeningRepository.findById(1L)).thenReturn(Optional.of(screening1));
        when(seatRepository.findByIdWithOptimisticLock(requestDto.getReservationSeatsId()))
                .thenReturn(List.of(seat1, seat2, new Seat(3L, theater, "A", 3),
                        new Seat(4L, theater, "A", 4), new Seat(5L, theater, "A", 5), new Seat(6L, theater, "A", 6)));

        SeatReservationException exception = assertThrows(SeatReservationException.class,
                () -> reservationService.reservationProcess(requestDto));

        assertEquals("한 번에 최대 5개 좌석만 예약할 수 있습니다.", exception.getMessage());
    }

    @Test
    void testInvalidSeatArrangement() {
        Seat seat3 = new Seat(3L, theater,"A", 4); // 불연속 좌석
        requestDto = new ReservationRequestDto(1L, 1L, List.of(1L, 2L, 3L));

        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(screeningRepository.findById(1L)).thenReturn(Optional.of(screening1));
        when(seatRepository.findByIdWithOptimisticLock(requestDto.getReservationSeatsId()))
                .thenReturn(List.of(seat1, seat2, seat3));

        SeatReservationException exception = assertThrows(SeatReservationException.class,
                () -> reservationService.reservationProcess(requestDto));

        assertEquals("좌석은 같은 행에서 연속된 형태로만 예약할 수 있습니다.", exception.getMessage());
    }

    @Test
    void testSeatAlreadyReserved() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(screeningRepository.findById(1L)).thenReturn(Optional.of(screening1));
        when(seatRepository.findByIdWithOptimisticLock(requestDto.getReservationSeatsId()))
                .thenReturn(List.of(seat1, seat2));
        when(reservationRepository.findReservedSeatsByScreeningId(1L))
                .thenReturn(List.of(seat1)); // seat1 이미 예약됨

        SeatReservationException exception = assertThrows(SeatReservationException.class,
                () -> reservationService.reservationProcess(requestDto));

        assertEquals("이미 예약된 좌석이 포함되어 있습니다: A1", exception.getMessage());
    }
}
