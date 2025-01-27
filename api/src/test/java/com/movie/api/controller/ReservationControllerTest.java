package com.movie.api.controller;

import com.movie.application.service.ReservationService;
import com.movie.domain.entity.Reservation;
import com.movie.domain.entity.Schedule;
import com.movie.domain.entity.Seat;
import com.movie.domain.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservationController.class)
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    @DisplayName("예매 성공 테스트")
    void reserveSuccess() throws Exception {
        // given
        String reservationNumber = UUID.randomUUID().toString().substring(0, 8);
        given(reservationService.reserve(1L, 1L, 1L)).willReturn(reservationNumber);

        // when & then
        mockMvc.perform(post("/api/v1/reservations")
                        .param("userId", "1")
                        .param("scheduleId", "1")
                        .param("seatId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(reservationNumber));
    }

    @Test
    @DisplayName("예매 조회 성공 테스트")
    void getReservationSuccess() throws Exception {
        // given
        Reservation reservation = createReservation();
        given(reservationService.getReservation("TEST123")).willReturn(reservation);

        // when & then
        mockMvc.perform(get("/api/v1/reservations/TEST123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reservationNumber").value("TEST123"));
    }

    @Test
    @DisplayName("사용자별 예매 목록 조회 성공 테스트")
    void getUserReservationsSuccess() throws Exception {
        // given
        Reservation reservation = createReservation();
        given(reservationService.getUserReservations(1L)).willReturn(Arrays.asList(reservation));

        // when & then
        mockMvc.perform(get("/api/v1/reservations/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].reservationNumber").value("TEST123"));
    }

    @Test
    @DisplayName("예매 취소 성공 테스트")
    void cancelReservationSuccess() throws Exception {
        // when & then
        mockMvc.perform(delete("/api/v1/reservations/TEST123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private Reservation createReservation() {
        User user = User.builder()
                .id(1L)
                .name("Test User")
                .email("test@test.com")
                .build();

        Schedule schedule = Schedule.builder()
                .id(1L)
                .startAt(LocalDateTime.now().plusDays(1))
                .endAt(LocalDateTime.now().plusDays(1).plusHours(2))
                .build();

        Seat seat = Seat.builder()
                .id(1L)
                .seatNumber("A1")
                .build();

        return Reservation.builder()
                .reservationNumber("TEST123")
                .user(user)
                .schedule(schedule)
                .seat(seat)
                .build();
    }
} 