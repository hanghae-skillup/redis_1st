package com.movie.domain.facade;

import com.movie.domain.movie.ReservationService;
import com.movie.domain.movie.SeatService;
import com.movie.domain.movie.domain.Reservation;
import com.movie.domain.movie.domain.Seat;
import com.movie.domain.movie.dto.command.ReservationCommand;
import com.movie.domain.userAccount.UserAccount;
import com.movie.domain.userAccount.UserAccountService;
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

    @Transactional
    public void makeReservation(ReservationCommand.GetReserveData getReserveData) {
        // 예약하려는 자리가 5개가 넘어가는지 확인
        Seat.isExceeded(getReserveData.seatIds().size());

        // 자리가 연속적인지 확인
        List<Seat> seats = seatService.getSeats(getReserveData.seatIds());
        Seat.isConsecutive(seats);

        // 유효셩 검증후 예약 진행
        UserAccount userAccount = userAccountService.getUserAccountByToken(getReserveData.token());

        // 자리가 선점되있는지 확인
        ReservationCommand.Get get = ReservationCommand.Get.of(getReserveData.scheduleId(), getReserveData.seatIds());
        List<Reservation> reservations = reservationService.getReservations(get);
        Reservation.isAlreadyReserved(reservations, seats);

        ReservationCommand.Reserve reserve =
                ReservationCommand.Reserve.of(getReserveData.scheduleId(), getReserveData.seatIds(), userAccount.getId());
        reservationService.makeReservation(reserve);
    }

}
