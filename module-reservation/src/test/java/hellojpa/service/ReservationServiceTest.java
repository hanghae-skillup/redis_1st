package hellojpa.service;

import hellojpa.dto.ReservationRequestDto;
import hellojpa.exception.SeatReservationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationTransactionalService reservationTransactionalService;

    @Mock
    private RedissonClient redissonClient;

    @Mock
    private RLock lock;

    @Mock
    private ReservationRateLimitService reservationRateLimitService;

    private ReservationRequestDto reservationRequestDto;

    @BeforeEach
    void setUp() {
        reservationRequestDto = new ReservationRequestDto(1L, 1L, List.of(1L, 2L));
    }

    @Test
    void testReserveSeats_lockAcquired() throws InterruptedException {
        when(redissonClient.getLock(anyString())).thenReturn(lock);
        when(lock.tryLock(4, 2, TimeUnit.SECONDS)).thenReturn(true);
        doNothing().when(reservationRateLimitService).enforceRateLimit(anyLong(), anyLong());
        doNothing().when(reservationTransactionalService).reservationProcess(any(ReservationRequestDto.class));

        reservationService.reserveSeats(reservationRequestDto);

        verify(reservationTransactionalService, times(1)).reservationProcess(reservationRequestDto);
        verify(lock, times(1)).unlock();
    }

    @Test
    void testReserveSeats_lockNotAcquired() throws InterruptedException {
        when(redissonClient.getLock(anyString())).thenReturn(lock);
        when(lock.tryLock(4, 2, TimeUnit.SECONDS)).thenReturn(false);
        doNothing().when(reservationRateLimitService).enforceRateLimit(anyLong(), anyLong());

        SeatReservationException exception = assertThrows(SeatReservationException.class, () -> {
            reservationService.reserveSeats(reservationRequestDto);
        });

        assertEquals("분산 락을 획득할 수 없습니다. 나중에 다시 시도해 주세요.", exception.getMessage());
    }

    @Test
    void testReserveSeats_lockAcquireFails_dueToInterrupt() throws InterruptedException {
        when(redissonClient.getLock(anyString())).thenReturn(lock);
        when(lock.tryLock(4, 2, TimeUnit.SECONDS)).thenThrow(InterruptedException.class);
        doNothing().when(reservationRateLimitService).enforceRateLimit(anyLong(), anyLong());

        SeatReservationException exception = assertThrows(SeatReservationException.class, () -> {
            reservationService.reserveSeats(reservationRequestDto);
        });

        assertEquals("분산 락 획득 중 오류 발생", exception.getMessage());
    }
}