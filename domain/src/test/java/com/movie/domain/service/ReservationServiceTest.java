package com.movie.domain.service;

import com.movie.domain.entity.*;
import com.movie.domain.exception.BusinessException;
import com.movie.domain.exception.ErrorCode;
import com.movie.domain.fixture.TestFixture;
import com.movie.domain.repository.ReservationRepository;
import com.movie.domain.repository.ScheduleRepository;
import com.movie.domain.repository.SeatRepository;
import com.movie.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private SeatRepository seatRepository;

    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        reservationService = new ReservationService(
                reservationRepository,
                userRepository,
                scheduleRepository,
                seatRepository
        );
    }

    @Test
    void reserve_Success() {
        // Given
        User user = TestFixture.createUser();
        Movie movie = TestFixture.createMovie();
        Schedule schedule = TestFixture.createSchedule(movie);
        Seat seat1 = TestFixture.createSeat();
        Seat seat2 = TestFixture.createSeat();
        List<Seat> seats = List.of(seat1, seat2);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(schedule));
        when(seatRepository.findAllById(List.of(1L, 2L))).thenReturn(seats);
        when(seatRepository.findReservedSeats(schedule)).thenReturn(List.of());
        when(reservationRepository.save(any(Reservation.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // When
        Reservation result = reservationService.reserve(1L, 1L, List.of(1L, 2L));

        // Then
        assertThat(result.getUser()).isEqualTo(user);
        assertThat(result.getSchedule()).isEqualTo(schedule);
        assertThat(result.getSeats()).containsExactlyElementsOf(seats);
    }

    @Test
    void reserve_UserNotFound() {
        // Given
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> reservationService.reserve(1L, 1L, List.of(1L)))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorCode.USER_NOT_FOUND.getMessage());
    }

    @Test
    void reserve_ScheduleNotFound() {
        // Given
        User user = TestFixture.createUser();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(scheduleRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> reservationService.reserve(1L, 1L, List.of(1L)))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorCode.SCHEDULE_NOT_FOUND.getMessage());
    }

    @Test
    void reserve_SeatNotFound() {
        // Given
        User user = TestFixture.createUser();
        Schedule schedule = TestFixture.createSchedule(TestFixture.createMovie());
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(schedule));
        when(seatRepository.findAllById(List.of(1L))).thenReturn(List.of());

        // When & Then
        assertThatThrownBy(() -> reservationService.reserve(1L, 1L, List.of(1L)))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorCode.SEAT_NOT_FOUND.getMessage());
    }

    @Test
    void reserve_SeatAlreadyReserved() {
        // Given
        User user = TestFixture.createUser();
        Schedule schedule = TestFixture.createSchedule(TestFixture.createMovie());
        Seat seat = TestFixture.createSeat();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(schedule));
        when(seatRepository.findAllById(List.of(1L))).thenReturn(List.of(seat));
        when(seatRepository.findReservedSeats(schedule)).thenReturn(List.of(seat));

        // When & Then
        assertThatThrownBy(() -> reservationService.reserve(1L, 1L, List.of(1L)))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorCode.SEAT_ALREADY_RESERVED.getMessage());
    }

    @Test
    void getReservation_Success() {
        // Given
        String reservationNumber = "TEST-123";
        Reservation reservation = TestFixture.createReservation(
                TestFixture.createUser(),
                TestFixture.createSchedule(TestFixture.createMovie()),
                List.of(TestFixture.createSeat())
        );
        when(reservationRepository.findByReservationNumber(reservationNumber))
                .thenReturn(Optional.of(reservation));

        // When
        Reservation result = reservationService.getReservation(reservationNumber);

        // Then
        assertThat(result).isEqualTo(reservation);
    }

    @Test
    void getReservation_NotFound() {
        // Given
        String reservationNumber = "TEST-123";
        when(reservationRepository.findByReservationNumber(reservationNumber))
                .thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> reservationService.getReservation(reservationNumber))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorCode.RESERVATION_NOT_FOUND.getMessage());
    }

    @Test
    void getUserReservations_Success() {
        // Given
        Long userId = 1L;
        Reservation reservation = TestFixture.createReservation(
                TestFixture.createUser(),
                TestFixture.createSchedule(TestFixture.createMovie()),
                List.of(TestFixture.createSeat())
        );
        when(reservationRepository.findByUserId(userId))
                .thenReturn(List.of(reservation));

        // When
        List<Reservation> result = reservationService.getUserReservations(userId);

        // Then
        assertThat(result).containsExactly(reservation);
    }

    @Test
    void cancelReservation_Success() {
        // Given
        String reservationNumber = "TEST-123";
        Reservation reservation = TestFixture.createReservation(
                TestFixture.createUser(),
                TestFixture.createSchedule(TestFixture.createMovie()),
                List.of(TestFixture.createSeat())
        );
        when(reservationRepository.findByReservationNumber(reservationNumber))
                .thenReturn(Optional.of(reservation));
        when(reservationRepository.save(any(Reservation.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // When
        reservationService.cancelReservation(reservationNumber);

        // Then
        assertThat(reservation.getStatus()).isEqualTo(ReservationStatus.CANCELLED);
    }
} 