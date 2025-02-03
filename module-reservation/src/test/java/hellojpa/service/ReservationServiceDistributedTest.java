/*
package hellojpa.service;

import hellojpa.domain.Seat;
import hellojpa.dto.ReservationRequestDto;
import hellojpa.repository.ReservationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.*;

@SpringBootTest
class ReservationServiceDistributedTest {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private RedissonClient redissonClient;  // RedissonClient 주입

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
                        // 락을 위한 키 정의
                        String lockKey = "lock:screening:1"; // 예시로 screeningId=1을 사용
                        RLock lock = redissonClient.getLock(lockKey);  // Redisson에서 락 객체 생성

                        // 락을 획득하고 예매 시도
                        boolean isLocked = lock.tryLock(10, 30, TimeUnit.SECONDS);  // 10초 동안 락을 기다리고, 30초 동안 락을 유지

                        if (isLocked) {
                            try {
                                // 예약 DTO 준비
                                ReservationRequestDto reservationRequestDto = new ReservationRequestDto(1L, 1L, List.of(1L));

                                // 예약 시도
                                reservationService.reserveSeats(reservationRequestDto);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            } finally {
                                // 락 해제
                                lock.unlock();
                            }
                        } else {
                            System.out.println("락을 획득할 수 없습니다.");
                        }
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
}*/
