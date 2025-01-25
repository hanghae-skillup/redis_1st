package com.example.reservation.service;

import com.example.jpa.entity.movie.Screening;
import com.example.jpa.entity.theater.Seat;
import com.example.jpa.entity.user.entity.User;
import com.example.jpa.repository.movie.ScreeningRepository;
import com.example.jpa.repository.movie.SeatRepository;
import com.example.jpa.repository.reservation.ReservationRepository;
import com.example.jpa.repository.user.UserRepository;
import com.example.message.MessageService;
import com.example.reservation.dto.ReservationRequest;
import com.example.reservation.dto.ReservationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock(lenient = true)
    private ScreeningRepository screeningRepository;

    @Mock(lenient = true)
    private ReservationRepository reservationRepository;

    @Mock(lenient = true)
    private SeatRepository seatRepository;

    @Mock(lenient = true)
    private UserRepository userRepository;

    @Mock(lenient = true)
    private MessageService messageService;

    @InjectMocks
    private ReservationService reservationService;

    private User user;

    private Screening screening;

    private ReservationRequest reservationRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        screening = new Screening();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(screeningRepository.findById(1L)).thenReturn(Optional.of(screening));
        when(reservationRepository.getReservationCount(1L, 1L)).thenReturn(0L);
    }

    @Test
    @DisplayName("인접하지 않은 좌석(A1, A3)에 대해 예외가 발생한다.")
    void testReserveSeat_SeatsNotAdjacent_A1_A3() {
        //given
        when(seatRepository.findByPositionAndScreeningId("A1", 1L)).thenReturn(Optional.of(new Seat()));
        when(seatRepository.findByPositionAndScreeningId("A3", 1L)).thenReturn(Optional.of(new Seat()));

        reservationRequest = new ReservationRequest(1L, 1L, Arrays.asList("A1", "A3"));

        //when & then
        assertThrows(IllegalArgumentException.class, () -> reservationService.reserveSeat(reservationRequest));
    }

    @Test
    @DisplayName("인접하지 않은 좌석(A1, B1)에 대해 예외가 발생한다.")
    void testReserveSeat_SeatsNotAdjacent_A1_B1() {
        //given
        when(seatRepository.findByPositionAndScreeningId("A1", 1L)).thenReturn(Optional.of(new Seat()));
        when(seatRepository.findByPositionAndScreeningId("B1", 1L)).thenReturn(Optional.of(new Seat()));

        reservationRequest = new ReservationRequest(1L, 1L, Arrays.asList("A1", "B1"));

        //when & then
        assertThrows(IllegalArgumentException.class, () -> reservationService.reserveSeat(reservationRequest));
    }

    @Test
    @DisplayName("인접한 좌석(A1, A2)에 대해 성공한다.")
    void testReserveSeat_SeatsAdjacent() {
        /** 테스트 실패. 존재하지 않는 좌석이라고 뜸
         *  when으로 stub 해줬는데 왜?
         */

        //given
        when(seatRepository.findByPositionAndScreeningId("A1", 1L)).thenReturn(Optional.of(new Seat()));
        when(seatRepository.findByPositionAndScreeningId("A2", 1L)).thenReturn(Optional.of(new Seat()));

        reservationRequest = new ReservationRequest(1L, 1L, Arrays.asList("A1", "A2"));

        //when
        ReservationResponse response = reservationService.reserveSeat(reservationRequest);

        //then
        assertNotNull(response);
    }
}
