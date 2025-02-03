package hellojpa.service;

import hellojpa.dto.ReservationRequestDto;
import hellojpa.exception.SeatReservationException;
import hellojpa.facade.OptimisticLockFacade;
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

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

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
    private OptimisticLockFacade optimisticLockFacade;

    private ReservationRequestDto reservationRequestDto;  // 테스트 데이터

    @BeforeEach
    void setUp() {
        // 테스트 데이터 설정
        reservationRequestDto = new ReservationRequestDto(1L, 1L, List.of(1L, 2L));
    }

    @Test
    void testReserveSeats_lockAcquired() throws InterruptedException {
        // 락이 획득되는 경우의 테스트
        when(redissonClient.getLock(anyString())).thenReturn(lock);
        when(lock.tryLock(1, 2, TimeUnit.SECONDS)).thenReturn(true);
        doNothing().when(reservationTransactionalService).reservationProcess(any(ReservationRequestDto.class));  // mock for reservationTransactionalService

        // 실제 reservationTransactionalService가 호출되는지 확인
        reservationService.reserveSeats(reservationRequestDto);

        // reservationTransactionalService가 한 번 호출되어야 함
        verify(reservationTransactionalService, times(1)).reservationProcess(reservationRequestDto);

        // 락이 해제되었는지 확인
        verify(lock, times(1)).unlock();
    }

    @Test
    void testReserveSeats_lockNotAcquired() throws InterruptedException {
        // 락이 획득되지 않는 경우의 테스트
        when(redissonClient.getLock(anyString())).thenReturn(lock);
        when(lock.tryLock(1, 2, TimeUnit.SECONDS)).thenReturn(false);

        // 예외가 발생해야 함
        SeatReservationException exception = assertThrows(SeatReservationException.class, () -> {
            reservationService.reserveSeats(reservationRequestDto);
        });

        // 예외 메시지가 정확한지 확인
        assertEquals("분산 락을 획득할 수 없습니다. 나중에 다시 시도해 주세요.", exception.getMessage());
    }

    @Test
    void testReserveSeats_lockAcquireFails_dueToInterrupt() throws InterruptedException {
        // 락을 획득하려고 시도하는 중에 InterruptedException이 발생하는 경우
        when(redissonClient.getLock(anyString())).thenReturn(lock);
        when(lock.tryLock(1, 2, TimeUnit.SECONDS)).thenThrow(InterruptedException.class);

        // 예외가 발생해야 함
        SeatReservationException exception = assertThrows(SeatReservationException.class, () -> {
            reservationService.reserveSeats(reservationRequestDto);
        });

        // 예외 메시지가 정확한지 확인
        assertEquals("분산 락 획득 중 오류 발생", exception.getMessage());
    }
}
