package com.movie.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.api.config.TestConfig;
import com.movie.api.request.ReservationRequest;
import com.movie.domain.entity.Reservation;
import com.movie.domain.fixture.TestFixture;
import com.movie.domain.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservationController.class)
@Import(TestConfig.class)
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReservationService reservationService;

    @Test
    void reserve() throws Exception {
        // Given
        ReservationRequest request = new ReservationRequest(1L, 1L, List.of(1L, 2L));
        Reservation reservation = TestFixture.createReservation(
                TestFixture.createUser(),
                TestFixture.createSchedule(TestFixture.createMovie()),
                List.of(TestFixture.createSeat(), TestFixture.createSeat())
        );

        when(reservationService.reserve(eq(1L), eq(1L), eq(List.of(1L, 2L))))
                .thenReturn(reservation);

        // When & Then
        mockMvc.perform(post("/api/v1/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.reservationNumber").value(reservation.getReservationNumber()));
    }

    @Test
    void getReservation() throws Exception {
        // Given
        String reservationNumber = "TEST-123";
        Reservation reservation = TestFixture.createReservation(
                TestFixture.createUser(),
                TestFixture.createSchedule(TestFixture.createMovie()),
                List.of(TestFixture.createSeat())
        );

        when(reservationService.getReservation(reservationNumber)).thenReturn(reservation);

        // When & Then
        mockMvc.perform(get("/api/v1/reservations/{reservationNumber}", reservationNumber))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.reservationNumber").value(reservation.getReservationNumber()));
    }

    @Test
    void getUserReservations() throws Exception {
        // Given
        Long userId = 1L;
        Reservation reservation = TestFixture.createReservation(
                TestFixture.createUser(),
                TestFixture.createSchedule(TestFixture.createMovie()),
                List.of(TestFixture.createSeat())
        );

        when(reservationService.getUserReservations(userId)).thenReturn(List.of(reservation));

        // When & Then
        mockMvc.perform(get("/api/v1/reservations/users/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data[0].reservationNumber").value(reservation.getReservationNumber()));
    }

    @Test
    void cancelReservation() throws Exception {
        // Given
        String reservationNumber = "TEST-123";

        // When & Then
        mockMvc.perform(delete("/api/v1/reservations/{reservationNumber}", reservationNumber))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void getAvailableSeats() throws Exception {
        // Given
        Long scheduleId = 1L;
        Reservation reservation = TestFixture.createReservation(
                TestFixture.createUser(),
                TestFixture.createSchedule(TestFixture.createMovie()),
                List.of(TestFixture.createSeat())
        );

        when(reservationService.getAvailableSeats(scheduleId)).thenReturn(List.of(reservation));

        // When & Then
        mockMvc.perform(get("/api/v1/reservations/schedules/{scheduleId}/seats", scheduleId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data[0].reservationNumber").value(reservation.getReservationNumber()));
    }
} 