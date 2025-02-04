package com.movie.domain.service;

import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.RateLimiter;
import com.movie.api.exception.RateLimitExceededException;
import com.movie.domain.entity.Reservation;
import com.movie.domain.entity.Schedule;
import com.movie.domain.entity.Seat;
import com.movie.domain.entity.User;
import com.movie.domain.fixture.TestFixture;
import com.movie.domain.repository.ReservationRepository;
import com.movie.domain.repository.ScheduleRepository;
import com.movie.domain.repository.SeatRepository;
import com.movie.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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

    @Mock
    private LoadingCache<String, RateLimiter> userReservationRateLimitCache;

    @Mock
    private RateLimiter rateLimiter;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    void reserve_Success() {
        // Given
        User user = TestFixture.createUser();
        Schedule schedule = TestFixture.createSchedule(TestFixture.createMovie());
        List<Seat> seats = List.of(TestFixture.createSeat(), TestFixture.createSeat());
        List<Long> seatIds = List.of(1L, 2L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(schedule));
        when(seatRepository.findAllById(seatIds)).thenReturn(seats);
        when(userReservationRateLimitCache.getUnchecked(any())).thenReturn(rateLimiter);
        when(rateLimiter.tryAcquire()).thenReturn(true);
        when(reservationRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        Reservation result = reservationService.reserve(1L, 1L, seatIds);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getUser()).isEqualTo(user);
        assertThat(result.getSchedule()).isEqualTo(schedule);
        assertThat(result.getSeats()).isEqualTo(seats);
    }

    @Test
    void reserve_RateLimitExceeded() {
        // Given
        User user = TestFixture.createUser();
        Schedule schedule = TestFixture.createSchedule(TestFixture.createMovie());
        List<Long> seatIds = List.of(1L, 2L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(schedule));
        when(userReservationRateLimitCache.getUnchecked(any())).thenReturn(rateLimiter);
        when(rateLimiter.tryAcquire()).thenReturn(false);

        // When & Then
        assertThatThrownBy(() -> reservationService.reserve(1L, 1L, seatIds))
                .isInstanceOf(RateLimitExceededException.class)
                .hasMessageContaining("Too many reservation attempts");
    }

    @Test
    void reserve_UserNotFound() {
        // Given
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> reservationService.reserve(1L, 1L, List.of(1L)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("User not found");
    }

    @Test
    void reserve_ScheduleNotFound() {
        // Given
        User user = TestFixture.createUser();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(scheduleRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> reservationService.reserve(1L, 1L, List.of(1L)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Schedule not found");
    }

    @Test
    void reserve_SeatNotFound() {
        // Given
        User user = TestFixture.createUser();
        Schedule schedule = TestFixture.createSchedule(TestFixture.createMovie());
        List<Long> seatIds = List.of(1L, 2L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(schedule));
        when(seatRepository.findAllById(seatIds)).thenReturn(List.of(TestFixture.createSeat()));
        when(userReservationRateLimitCache.getUnchecked(any())).thenReturn(rateLimiter);
        when(rateLimiter.tryAcquire()).thenReturn(true);

        // When & Then
        assertThatThrownBy(() -> reservationService.reserve(1L, 1L, seatIds))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Some seats not found");
    }
} 