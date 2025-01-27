package hellojpa.facade;

import hellojpa.domain.Seat;
import hellojpa.dto.ReservationDto;
import hellojpa.repository.ReservationRepository;
import hellojpa.service.ReservationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class OptimisticLockFacadeTest {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void testConcurrentSeatReservation() throws InterruptedException {
        // 10명이 동시에 예매하려고 시도할 때 그 중 한 명만 예매 성공
        int userCount = 10;
        CountDownLatch latch = new CountDownLatch(userCount);

        ExecutorService executor = Executors.newFixedThreadPool(userCount);

        for (int i = 0; i < userCount; i++) {

            executor.submit(new Callable<Void>() {
                @Override
                @Transactional
                public Void call() throws Exception {
                    try {
                        ReservationDto reservationDto = new ReservationDto();
                        reservationDto.setUserId(1L);
                        reservationDto.setScreeningId(1L);
                        reservationDto.setReservationSeatsId(List.of(1L));

                        // 예약 시도
                        reservationService.reserveSeats(reservationDto);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    } finally {
                        latch.countDown();
                    }
                    return null;
                }
            });
        }

        latch.await();

        // 예매된 좌석이 1개여야만 성공
        List<Seat> reservedSeats = reservationRepository.findReservedSeatsByScreeningId(1L);
        Assertions.assertThat(reservedSeats.size()).isEqualTo(1);
    }
}