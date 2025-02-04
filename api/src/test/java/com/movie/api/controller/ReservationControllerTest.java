package com.movie.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.api.config.TestConfig;
import com.movie.api.dto.request.ReservationRequest;
import com.movie.domain.entity.*;
import com.movie.domain.repository.ReservationRepository;
import com.movie.domain.repository.ScheduleRepository;
import com.movie.domain.repository.SeatRepository;
import com.movie.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
    "spring.main.allow-bean-definition-overriding=true",
    "spring.data.redis.enabled=false"
})
@AutoConfigureMockMvc
@Import(TestConfig.class)
@ActiveProfiles("test")
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @BeforeEach
    void setUp() {
        reservationRepository.deleteAll();
        userRepository.deleteAll();
        scheduleRepository.deleteAll();
        seatRepository.deleteAll();

        User user = User.builder()
                .email("test@test.com")
                .password("password")
                .phoneNumber("01012345678")
                .name("Test User")
                .build();
        userRepository.save(user);

        Schedule schedule = Schedule.builder()
                .movieId(1L)
                .theaterId(1L)
                .startTime(LocalDateTime.now().plusDays(1))
                .endTime(LocalDateTime.now().plusDays(1).plusHours(2))
                .build();
        scheduleRepository.save(schedule);

        Seat seat = Seat.builder()
                .scheduleId(schedule.getId())
                .theaterId(1L)
                .seatNumber("A1")
                .rowNumber(1)
                .columnNumber(1)
                .build();
        seatRepository.save(seat);
    }

    @Test
    @DisplayName("예매 성공 테스트")
    void reserveSuccess() throws Exception {
        User user = userRepository.findAll().get(0);
        Schedule schedule = scheduleRepository.findAll().get(0);
        Seat seat = seatRepository.findAll().get(0);

        ReservationRequest request = new ReservationRequest();
        request.setUserId(user.getId());
        request.setScheduleId(schedule.getId());
        request.setSeatId(seat.getId());

        mockMvc.perform(post("/api/v1/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("예매 조회 성공 테스트")
    void getReservationSuccess() throws Exception {
        User user = userRepository.findAll().get(0);
        Schedule schedule = scheduleRepository.findAll().get(0);
        Seat seat = seatRepository.findAll().get(0);

        Reservation reservation = Reservation.builder()
                .userId(user.getId())
                .scheduleId(schedule.getId())
                .seatId(seat.getId())
                .reservationNumber("TEST001")
                .build();
        reservationRepository.save(reservation);

        mockMvc.perform(get("/api/v1/reservations/" + reservation.getId()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("사용자별 예매 목록 조회 성공 테스트")
    void getUserReservationsSuccess() throws Exception {
        User user = userRepository.findAll().get(0);

        mockMvc.perform(get("/api/v1/reservations/users/" + user.getId()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("예매 취소 성공 테스트")
    void cancelReservationSuccess() throws Exception {
        User user = userRepository.findAll().get(0);
        Schedule schedule = scheduleRepository.findAll().get(0);
        Seat seat = seatRepository.findAll().get(0);

        Reservation reservation = Reservation.builder()
                .userId(user.getId())
                .scheduleId(schedule.getId())
                .seatId(seat.getId())
                .reservationNumber("TEST001")
                .build();
        reservationRepository.save(reservation);

        mockMvc.perform(delete("/api/v1/reservations/" + reservation.getId()))
                .andExpect(status().isOk());
    }
} 