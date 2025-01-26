package com.hanghae.domain.service;

import com.hanghae.domain.model.Member;
import com.hanghae.domain.model.ScreenSeatLayout;
import com.hanghae.domain.model.ScreeningSchedule;
import com.hanghae.domain.model.TicketReservation;
import com.hanghae.domain.model.enums.ScreenSeat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    /* 1인당 5개로 예매 제한하기 */
    public void validateReservationSeatLimit(int reservedTicketCount, int seatCount) {
        if (reservedTicketCount + seatCount > 5) {
            throw new IllegalArgumentException("상영시간별 예매는 최대 5개까지 할 수 있습니다.");
        }
    }

    /* 좌석 중복 여부 검증 */
    public void validateSeatAvailability(int duplicateReservationCount) {
        if (duplicateReservationCount > 0) {
            throw new IllegalArgumentException("선택한 좌석은 이미 예매되었습니다.");
        }
    }

    /* 좌석 범위 검증 */
    public void validateSeatLimit(ScreenSeatLayout screenSeatLayout, List<ScreenSeat> selectedSeats) {
        for (ScreenSeat seat : selectedSeats) {
            if (seat.getSeatNumber() > screenSeatLayout.getMaxSeatNumber()) {
                throw new IllegalArgumentException(
                        String.format("선택한 좌석 %s은(는) 예매 가능한 좌석 범위를 초과합니다. (최대 %s 좌석까지 가능)",
                                seat.name(), screenSeatLayout.getSeatRow() + String.format("%02d", screenSeatLayout.getMaxSeatNumber())));
            }
        }
    }

    /* 예매 정보 생성 */
    public List<TicketReservation> createTicketReservations(ScreeningSchedule screeningSchedule, Member member, ScreenSeat screenSeat, ScreenSeatLayout screenSeatLayout, int seatCount) {
        //선택좌석부터 연결된 좌석 조회
        List<ScreenSeat> selectedSeats = ScreenSeat.getSelectedConnectedSeats(screenSeat, seatCount);
        
        //좌석 범위 검증
        validateSeatLimit(screenSeatLayout, selectedSeats);

        // 좌석목록에 전부에 대해 영화예약 도메인 객체 생성하여 리턴
        return selectedSeats.stream()
                .map(seat -> TicketReservation.create(screeningSchedule, member, seat))
                .collect(Collectors.toList());
    }
}
