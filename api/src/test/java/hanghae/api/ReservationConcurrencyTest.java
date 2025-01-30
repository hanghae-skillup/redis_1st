package hanghae.api;

import hanghae.application.dto.request.ReservationRequest;
import hanghae.application.port.ReservationService;
import hanghae.infrastructure.repository.JpaReservationRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("dev")
class ReservationConcurrencyTest {

    @Autowired private ReservationService reservationService;
    @Autowired private JpaReservationRepository jpaReservationRepository;

    @AfterEach
    void clean() {
        jpaReservationRepository.deleteAll();
    }

    @Test
    @Transactional
    // 이거 롤백 어떻게 하지..?
    void reservePessimisticLockTest() throws InterruptedException {
        Long memberId = 5L;
        Long screenId = 50L;
        Long scheduleId = 500L;
        String seatName = "A1";

        int threads = 10;
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(threads);

        for (int i = 0; i < threads; i++) {
            new Thread(() -> {
                try {
                    startLatch.await();

                    ReservationRequest request = new ReservationRequest(
                            memberId,
                            screenId,
                            scheduleId,
                            List.of(seatName)
                    );

                    reservationService.reserveSeat(request);
                    System.out.println("Reserved seat " + seatName);
                } catch (Exception e) {
                    System.out.println("Thread Exception: " + e.getMessage());
                } finally {
                    endLatch.countDown();
                }
            }).start();
        }

        startLatch.countDown();
        endLatch.await();

        Assertions.assertThat(jpaReservationRepository.findAll()).hasSize(1);
    }
}