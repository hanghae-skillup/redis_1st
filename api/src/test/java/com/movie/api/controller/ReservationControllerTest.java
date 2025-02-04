package com.movie.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.api.request.ReservationRequest;
import com.movie.api.support.IntegrationTest;
import com.movie.common.exception.RateLimitExceededException;
import com.movie.domain.entity.Reservation;
import com.movie.domain.fixture.TestFixture;
import com.movie.domain.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ReservationControllerTest extends IntegrationTest {

    @MockBean
    private ReservationService reservationService;

    @Test
    void reserve_Success() throws Exception {
        // Given
        ReservationRequest request = new ReservationRequest(1L, List.of(1L, 2L));
        Reservation reservation = TestFixture.createReservation(
                TestFixture.createUser(),
                TestFixture.createSchedule(TestFixture.createMovie()),
                List.of(TestFixture.createSeat(), TestFixture.createSeat())
        );

        when(reservationService.reserve(eq(1L), eq(1L), any()))
                .thenReturn(reservation);

        // When & Then
        mockMvc.perform(post("/api/v1/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.code").value("SUCCESS"))
                .andExpect(jsonPath("$.data.reservationNumber").value(reservation.getReservationNumber()));
    }

    @Test
    void reserve_RateLimitExceeded() throws Exception {
        // Given
        ReservationRequest request = new ReservationRequest(1L, List.of(1L, 2L));

        when(reservationService.reserve(eq(1L), eq(1L), any()))
                .thenThrow(new RateLimitExceededException("Too many reservation attempts"));

        // When & Then
        mockMvc.perform(post("/api/v1/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isTooManyRequests())
                .andExpect(jsonPath("$.status").value("TOO_MANY_REQUESTS"))
                .andExpect(jsonPath("$.code").value("RATE_LIMIT_EXCEEDED"))
                .andExpect(jsonPath("$.message").value("Too many reservation attempts"));
    }
} 