package hellojpa.service;

import hellojpa.dto.ReservationRequestDto;
import hellojpa.exception.SeatReservationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {

    private final ReservationTransactionalService reservationTransactionalService;
    private final RedissonClient redissonClient;
    private final ReservationRateLimitService reservationRateLimitService;

    //@DistributedLock(key = "#reservationDto.screeningId")
    public void reserveSeats(ReservationRequestDto reservationRequestDto) {

        String lockKey = "lock:screening:" + reservationRequestDto.getScreeningId(); // 락 키
        RLock lock = redissonClient.getLock(lockKey);  // Redisson에서 락 객체 생성
        boolean isLocked = false;

        reservationRateLimitService.enforceRateLimit(reservationRequestDto.getUserId(), reservationRequestDto.getScreeningId());
        try {
            // waitTime 동안 락을 시도하고, leaseTime 동안 락을 유지
            isLocked = lock.tryLock(4, 2, TimeUnit.SECONDS);

            if (isLocked) {
                try {
                    // 실제 예약 처리
                    reservationTransactionalService.reservationProcess(reservationRequestDto);
                } catch (Exception e) {
                    // 예약 실패 시 예외처리
                    log.warn("예약 처리 실패, 좌석 예약 예외 발생: {}", e.getMessage());
                    throw e;
                }

            } else {
                log.error("분산 락을 획득할 수 없습니다. 나중에 다시 시도해 주세요.");
                throw new SeatReservationException("분산 락을 획득할 수 없습니다. 나중에 다시 시도해 주세요.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("분산 락 획득 중 오류 발생", e);
            throw new SeatReservationException("분산 락 획득 중 오류 발생", e);
        } finally {
            if (isLocked) {
                lock.unlock();
            }
        }
    }
}