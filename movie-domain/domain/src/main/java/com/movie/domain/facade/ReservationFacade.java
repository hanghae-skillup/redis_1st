package com.movie.domain.facade;

import com.movie.domain.exception.ApplicationException;
import com.movie.domain.exception.ErrorCode;
import com.movie.domain.movie.ReservationService;
import com.movie.domain.movie.dto.command.ReservationCommand;
import com.movie.domain.userAccount.UserAccount;
import com.movie.domain.userAccount.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationFacade {

    private final UserAccountService userAccountService;
    private final ReservationService reservationService;

    public void makeReservation(ReservationCommand.Reserve reserve) {
        if (reserve.seatIds().size() > 5) {
            throw new ApplicationException(
                    ErrorCode.RESERVATION_LIMIT_EXCEEDED,
                    "seats are exceeded over 5 - requested seats : %d".formatted(reserve.seatIds().size()));
        }

        UserAccount userAccount = userAccountService.getUserAccountByToken(reserve.token());
        // 자리가 연속적인지 확인
        // 자리가 선점되 있는지 확인

        // reservation 스케줄 양 만큼 미리 등록
        // seat은 25개 지정해서 등록


    }

}
