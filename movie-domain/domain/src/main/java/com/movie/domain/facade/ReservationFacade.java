package com.movie.domain.facade;

import com.movie.domain.movie.ReservationService;
import com.movie.domain.movie.SeatService;
import com.movie.domain.movie.domain.Reservation;
import com.movie.domain.movie.domain.Seat;
import com.movie.domain.movie.dto.command.ReservationCommand;
import com.movie.domain.userAccount.UserAccount;
import com.movie.domain.userAccount.UserAccountService;
import com.movie.redis.lock.DistributedLockExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationFacade {

    private final UserAccountService userAccountService;
    private final SeatService seatService;
    private final ReservationService reservationService;

    private final DistributedLockExecutor executor;

    /**
        메서드 전체에 트랜젝션을 선언한 형태로 분산락 적용
     */
    @Transactional
    public void makeReservations(ReservationCommand.GetReserveData getReserveData) {
        // 예약하려는 좌석이 5개 이상인지 확인
        Seat.isExceeded(getReserveData.seatIds().size());

        // 예역하려는 좌석이 연속적인지 확인
        List<Seat> seats = seatService.getSeats(getReserveData.seatIds());
        Seat.isConsecutive(seats);

        // 토큰으로 사용자 조회
        UserAccount userAccount = userAccountService.getUserAccountByToken(getReserveData.token());

        // 자리가 선점되있는지 확인
        ReservationCommand.Get get = ReservationCommand.Get.of(getReserveData.scheduleId(), getReserveData.seatIds());
        List<Reservation> reservations = reservationService.getReservations(get);
        Reservation.isAlreadyReserved(reservations, seats);

        ReservationCommand.Reserve reserve =
                ReservationCommand.Reserve.of(getReserveData.scheduleId(), getReserveData.seatIds(), userAccount.getId());
        reservationService.makeReservation(reserve);
    }

    /**
         부하를 적절히 분산하기 위해 동시성이 발생하는 로직에 함수형 분산락 적용
     */
    public void makeReservationsByFunctionalLock(ReservationCommand.GetReserveData getReserveData) {
        // 예약하려는 자리가 5개가 넘어가는지 확인
        Seat.isExceeded(getReserveData.seatIds().size());

        // 자리가 연속적인지 확인
        List<Seat> seats = seatService.getSeats(getReserveData.seatIds());
        Seat.isConsecutive(seats);

        // 토큰으로 사용자 조회
        UserAccount userAccount = userAccountService.getUserAccountByToken(getReserveData.token());

        /**
            함수형 기반 분산락 적용 - 분산락 + 낙관적 락이 적용됨
         */
        String lockName = "%s:%s:%s".formatted("reservation", System.currentTimeMillis(), getReserveData.token());
        executor.executeWithLock(lockName, () -> {
            reservationService.reserveSeats(getReserveData.scheduleId(), getReserveData.seatIds(), seats, userAccount.getId());
        });
    }

}
