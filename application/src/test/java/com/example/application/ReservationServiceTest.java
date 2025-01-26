package com.example.application;


import com.example.application.reservation.dto.ServiceReservationResponseDto;
import com.example.application.reservation.service.ReservationService;
import com.example.common.util.BusinessException;
import com.example.common.util.ErrorCode;
import com.example.domain.member.entity.Member;
import com.example.domain.member.repository.MemberRepository;
import com.example.domain.reservation.entity.Reservation;
import com.example.domain.reservation.entity.ReservationSeat;
import com.example.domain.reservation.repository.ReservationRepository;
import com.example.domain.reservation.repository.ReservationSeatRepository;
import com.example.domain.screening.entity.Screening;
import com.example.domain.screening.repository.ScreeningRepository;
import com.example.domain.seat.entity.Seat;
import com.example.domain.seat.repository.SeatRepository;
import com.example.domain.theater.entity.Theater;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private ScreeningRepository screeningRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ReservationSeatRepository reservationSeatRepository;

    @Mock
    private SeatRepository seatRepository;

    private Member member;
    private Screening screening;
    private Theater theater;
    private List<Seat> seats;

    @BeforeEach
    void setUp() {
        // 테스트용 데이터 초기화
        member = Member.builder().id(1L).build();

        theater = Theater.builder().id(1L).build();

        screening = Screening.builder().id(1L).startTime(LocalDateTime.now()).endTime(LocalDateTime.now().plusHours(2)).theater(theater).build();

        seats = Arrays.asList(
                Seat.builder().id(1L).seatRow("A").seatCol("1").status(Seat.Status.AVAILABLE).build(),
                Seat.builder().id(2L).seatRow("A").seatCol("2").status(Seat.Status.AVAILABLE).build(),
                Seat.builder().id(3L).seatRow("A").seatCol("3").status(Seat.Status.AVAILABLE).build()
        );
    }

    @Test
    @DisplayName("성공 시나리오")
    void reserveMovie_success() {
        // given
        Mockito.when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        Mockito.when(screeningRepository.findScreeningWithTheather(1L)).thenReturn(Optional.of(screening));
        Mockito.when(seatRepository.findAvailableSeats(1L, Arrays.asList(1L, 2L, 3L))).thenReturn(seats);

        //when
        ServiceReservationResponseDto response = reservationService.reserveMovie(1L, 1L, Arrays.asList(1L, 2L, 3L));

        //then
        assertNotNull(response);
        verify(reservationRepository, times(1)).save(any(Reservation.class));
        verify(reservationSeatRepository, times(seats.size())).save(any(ReservationSeat.class));

    }

    @Test
    @DisplayName("좌석 수 매칭 테스트")
    void reserveMovie_seatMismatch() {
        // given
        Mockito.when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        Mockito.when(screeningRepository.findScreeningWithTheather(1L)).thenReturn(Optional.of(screening));
        Mockito.when(seatRepository.findAvailableSeats(1L, Arrays.asList(1L, 2L, 3L))).thenReturn(Arrays.asList(seats.get(0), seats.get(1)));


        //when
        BusinessException exception = assertThrows(BusinessException.class,
                () -> reservationService.reserveMovie(1L, 1L, Arrays.asList(1L, 2L, 3L)));
        assertEquals(ErrorCode.INVALID_INPUT_VALUE, exception.getErrorCode());
    }

    @Test
    @DisplayName("좌석 연속성 테스트 ")
    void reserveMovie_seatsNotConsecutive() {
        // given
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(screeningRepository.findScreeningWithTheather(1L)).thenReturn(Optional.of(screening));
        List<Seat> nonConsecutiveSeats = Arrays.asList(
                Seat.builder().id(1L).seatRow("A").seatCol("1").status(Seat.Status.AVAILABLE).build(),
                Seat.builder().id(2L).seatRow("A").seatCol("3").status(Seat.Status.AVAILABLE).build() // 연속되지 않음
        );
        when(seatRepository.findAvailableSeats(1L, List.of(1L, 2L))).thenReturn(nonConsecutiveSeats);

        // when & then
        BusinessException exception = assertThrows(BusinessException.class,
                () -> reservationService.reserveMovie(1L, 1L, List.of(1L, 2L)));
        assertEquals(ErrorCode.INVALID_INPUT_VALUE, exception.getErrorCode());
    }
}
