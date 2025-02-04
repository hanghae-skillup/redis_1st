package com.movie.application.service;

import com.movie.domain.entity.Reservation;
import com.movie.domain.entity.Schedule;
import com.movie.domain.entity.Seat;
import com.movie.domain.entity.User;
import com.movie.domain.repository.ReservationRepository;
import com.movie.domain.repository.ScheduleRepository;
import com.movie.domain.repository.SeatRepository;
import com.movie.domain.repository.UserRepository;
import com.movie.exception.BusinessException;
import com.movie.exception.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ScheduleRepository scheduleRepository;
    @Mock
    private SeatRepository seatRepository;

    @InjectMocks
    private ReservationService reservationService;

    private User user;
    private Schedule schedule;
    private Seat seat;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .name("Test User")
                .email("test@test.com")
                .password("password")
                .phoneNumber("01012345678")
                .build();

        schedule = Schedule.builder()
                .id(1L)
                .movieId(1L)
                .theaterId(1L)
                .startTime(LocalDateTime.now().plusDays(1))
                .endTime(LocalDateTime.now().plusDays(1).plusHours(2))
                .build();

        seat = Seat.builder()
                .id(1L)
                .theaterId(1L)
                .seatNumber("A1")
                .rowNumber(1)
                .columnNumber(1)
                .build();
    }

    @Test
    @DisplayName("예매 성공 테스트")
    void reserveSuccess() {
        // given
        given(userRepository.findById(1L)).willReturn(Optional.of(user));
        given(scheduleRepository.findById(1L)).willReturn(Optional.of(schedule));
        given(seatRepository.findById(1L)).willReturn(Optional.of(seat));
        given(reservationRepository.existsByScheduleIdAndSeatId(1L, 1L)).willReturn(false);
        given(reservationRepository.save(any(Reservation.class))).willAnswer(invocation -> invocation.getArgument(0));

        // when
        String reservationNumber = reservationService.reserve(1L, 1L, 1L);

        // then
        assertThat(reservationNumber).isNotNull();
        verify(reservationRepository).save(any(Reservation.class));
    }

    @Test
    @DisplayName("이미 예약된 좌석 예매 실패 테스트")
    void reserveFailWhenSeatAlreadyReserved() {
        // given
        given(userRepository.findById(1L)).willReturn(Optional.of(user));
        given(scheduleRepository.findById(1L)).willReturn(Optional.of(schedule));
        given(seatRepository.findById(1L)).willReturn(Optional.of(seat));
        given(reservationRepository.existsByScheduleIdAndSeatId(1L, 1L)).willReturn(true);

        // when & then
        assertThatThrownBy(() -> reservationService.reserve(1L, 1L, 1L))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorCode.SEAT_ALREADY_RESERVED.getMessage());

        verify(reservationRepository, never()).save(any(Reservation.class));
    }

    @Test
    @DisplayName("존재하지 않는 사용자로 예매 실패 테스트")
    void reserveFailWithNonExistentUser() {
        // given
        given(userRepository.findById(999L)).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> reservationService.reserve(999L, 1L, 1L))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorCode.USER_NOT_FOUND.getMessage());

        verify(reservationRepository, never()).save(any(Reservation.class));
    }
} 