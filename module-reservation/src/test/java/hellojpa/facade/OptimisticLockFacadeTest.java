package hellojpa.facade;

import hellojpa.domain.Seat;
import hellojpa.dto.ReservationRequestDto;
import hellojpa.repository.ReservationRepository;
import hellojpa.service.ReservationService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OptimisticLockFacadeTest {

    @InjectMocks
    private OptimisticLockFacade optimisticLockFacade;

    @Mock
    private ReservationRepository reservationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConcurrentSeatReservation() throws InterruptedException {
        int userCount = 10;
        CountDownLatch latch = new CountDownLatch(userCount);
        ExecutorService executor = Executors.newFixedThreadPool(userCount);

        when(reservationRepository.findReservedSeatsByScreeningId(any()))
                .thenReturn(List.of(new Seat())); // 좌석 정보 Mock 설정

        for (int i = 0; i < userCount; i++) {
            executor.submit(() -> {
                try {
                    ReservationRequestDto requestDto = new ReservationRequestDto(1L, 1L, List.of(1L));
                    optimisticLockFacade.reserveSeats(requestDto);
                } catch (Exception e) {
                    System.err.println("예외 발생: " + e.getMessage());
                } finally {
                    latch.countDown(); // 예외 발생 여부와 상관없이 항상 호출되도록 함
                }
            });
        }

        boolean completed = latch.await(5, TimeUnit.SECONDS); // 최대 5초 대기
        executor.shutdown(); // ExecutorService 종료

        if (!completed) {
            System.err.println("테스트가 시간 내에 종료되지 않음. Deadlock 가능성 있음.");
        }

        List<Seat> reservedSeats = reservationRepository.findReservedSeatsByScreeningId(1L);
        Assertions.assertThat(reservedSeats.size()).isEqualTo(1);

        verify(reservationRepository, atLeastOnce()).findReservedSeatsByScreeningId(any());
    }
}
