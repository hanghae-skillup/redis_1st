package com.hanghae.domain.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum ScreenSeat {
    A01, A02, A03, A04, A05,
    B01, B02, B03, B04, B05,
    C01, C02, C03, C04, C05,
    D01, D02, D03, D04, D05,
    E01, E02, E03, E04, E05;

    // 좌석 알파벳 추출
    public String getSeatRow() {
        return this.name().substring(0, 1); // 첫 번째 문자 가져오기(A, B, C ...)
    }

    // 좌석 번호 추출
    public int getSeatNumber() {
        return Integer.parseInt(this.name().substring(1)); // 숫자 부분 가져오기 (01, 02 ...)
    }

    // 좌석 알파벳 + 번호로 Enum 찾기
    public static ScreenSeat fromRowAndNumber(String prefix, int number) {
        String seatName = prefix + String.format("%02d", number); // "A" + "01" -> "A01"
        return ScreenSeat.valueOf(seatName);
    }

    // 연결된 다음 좌석 찾기
    public static ScreenSeat getNextSeat(ScreenSeat seat) {
        String seatRow = seat.getSeatRow();
        int nextSeatNumber = seat.getSeatNumber() + 1;
        try {
            return fromRowAndNumber(seatRow, nextSeatNumber);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("다음 좌석이 존재하지 않습니다.");
        }
    }

    // 시작 좌석과 예약 인원수를 받아서 입력 받은 좌석부터 숫자만큼 연결된 좌석 리턴
    public static List<ScreenSeat> getSelectedConnectedSeats(ScreenSeat startSeat, int count) {
        List<ScreenSeat> resultSeats = new ArrayList<>();
        resultSeats.add(startSeat);

        ScreenSeat currentSeat = startSeat;
        for (int i = 1; i < count; i++) {
            currentSeat = getNextSeat(currentSeat); // 다음 좌석 가져오기
            resultSeats.add(currentSeat);
        }

        return resultSeats;
    }
}
