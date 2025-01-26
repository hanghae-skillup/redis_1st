package com.example.application.SeatReservation;

import com.example.application.SeatReservation.redis.DistributedLockManager;
import com.example.domain.screening.service.ScreeningService;
import com.example.domain.seatReservation.service.SeatReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class SeatReservationFacade {

    private final SeatReservationService seatReservationService;
    private final ScreeningService screeningService;
    private final DistributedLockManager distributedLockManager; // DistributedLockManager 주입

    //@Transactional
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public boolean reserve(Long screeningId, Long userId, String seatNumber, Integer count) {


        String lockKey = "seatReservation:" + screeningId + ":" + seatNumber;
        log.info("Lock Key: {}", lockKey);
        try {
            // 분산 락을 사용하여 비즈니스 로직 보호
            return distributedLockManager.executeWithLock(
                    lockKey,
                    3000,
                    3000,
                    () -> {
                        var screening = screeningService.getScreeningInfo(screeningId);
                        return seatReservationService.reserve(count, userId, seatNumber, screening);
                    }
            );
        } catch (Exception e) {
            log.error("Failed to reserve seat. ScreeningId: {}, UserId: {}, SeatNumber: {}",
                    screeningId, userId, seatNumber, e);
            return false; // 락 획득 실패 또는 기타 오류 시 false 반환
        }


    }


}