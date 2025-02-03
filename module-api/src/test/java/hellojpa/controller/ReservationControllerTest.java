/*
package hellojpa.controller;

import hellojpa.dto.ReservationRequestDto;
import hellojpa.service.ReservationService;
import hellojpa.service.ReservationTransactionalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReservationService reservationService;

    @Mock
    private RedissonClient redissonClient;  // RedissonClient 목킹

    @Mock
    private ReservationTransactionalService reservationTransactionalService;  // ReservationTransactionalService 목킹

    @BeforeEach
    void setUp() {
        // 실제 의존성 주입을 위한 설정
        reservationService = new ReservationService(reservationTransactionalService, redissonClient);
    }

    @Test
    void reserveSeats_ShouldReturnOk() throws Exception {
        // Given
        ReservationRequestDto requestDto = new ReservationRequestDto(1L, 2L, List.of(1L, 2L));

        // Redisson의 락을 목킹
        RLock mockLock = mock(RLock.class);
        when(redissonClient.getLock(anyString())).thenReturn(mockLock);
        when(mockLock.tryLock(anyLong(), anyLong(), any())).thenReturn(true);

        // When & Then
        mockMvc.perform(post("/reservation/movie")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"screeningId\":1, \"seats\":2}"))  // JSON 데이터 넣기
                .andExpect(status().isOk());

        // 예약 처리 메서드가 호출되었는지 확인
        verify(reservationTransactionalService, times(1)).reservationProcess(any());
        verify(mockLock, times(1)).unlock();
    }

    @Test
    void reserveSeats_ShouldReturnConflict_WhenLockFails() throws Exception {
        // Given
        ReservationRequestDto requestDto = new ReservationRequestDto(1L, 2L, List.of(1L, 2L));

        // Redisson의 락을 목킹
        RLock mockLock = mock(RLock.class);
        when(redissonClient.getLock(anyString())).thenReturn(mockLock);
        when(mockLock.tryLock(anyLong(), anyLong(), any())).thenReturn(false);

        // When & Then
        mockMvc.perform(post("/reservation/movie")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"screeningId\":1, \"seats\":2}"))  // JSON 데이터 넣기
                .andExpect(status().isConflict());  // 409 Conflict로 반환되는지 확인

        // 락을 얻지 못한 경우 예약 처리 메서드는 호출되지 않음
        verify(reservationTransactionalService, never()).reservationProcess(any());
    }
}
*/