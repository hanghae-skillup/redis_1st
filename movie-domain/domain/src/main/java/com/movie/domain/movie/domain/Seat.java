package com.movie.domain.movie.domain;

import com.movie.common.enums.AxisY;
import com.movie.domain.exception.ApplicationException;
import com.movie.domain.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat {

    private Long id;
    private String seatNumber;
    public AxisY axisY;
    private Integer axisX;

    public Seat(Long id, String seatNumber, AxisY axisY, Integer axisX) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.axisY = axisY;
        this.axisX = axisX;
    }

    public static Seat of(Long id, String seatNumber, AxisY axisY, Integer axiosX) {
        return new Seat(id, seatNumber, axisY, axiosX);
    }

    public static Seat of(String seatNumber, AxisY axisY, Integer axiosX) {
        return new Seat(null, seatNumber, axisY, axiosX);
    }

    public static Seat of(AxisY axisY, Integer axiosX) {
        return new Seat(null, null, axisY, axiosX);
    }

    // 5자리 이상인지 확인
    public static void isExceeded(Integer seatCount) {
        if (seatCount > 5) {
            throw new ApplicationException(
                    ErrorCode.LIMIT_EXCEEDED,
                    "seats are exceeded over 5 - requested seats : %d".formatted(seatCount)
            );
        }
    }

    // 연속된 자리인지 확인
    public static void isConsecutive(List<Seat> seats) {
        if (seats.isEmpty()) {          // 좌석이 비어있는 경우 예외
            throw new ApplicationException(ErrorCode.UNABLE_TO_RESERVE, "Seats list is empty");
        }
        if (seats.size() == 1) return;  // 자리 하나면 연속성 확인 필요 없이 통과

        boolean isConsecutive = true;

        // 가변 리스트로 변환
        List<Seat> mutableSeats = new ArrayList<>(seats);

        // X축(행)과 Y축(열) 기준으로 정렬
        mutableSeats.sort(Comparator.comparing(Seat::getAxisY).thenComparing(Seat::getAxisX));


        // 1. 좌석을 X축(행)과 Y축(열) 기준으로 정렬
//        seats.sort(Comparator.comparing(Seat::getAxisY).thenComparing(Seat::getAxisX));
//        try {
//            seats.sort((seat1, seat2) -> {
//                // 먼저 AxisY를 비교
//                int axisYComparison = seat1.getAxisY().compareTo(seat2.getAxisY());
//
//                // AxisY가 같으면 AxisX를 비교
//                if (axisYComparison == 0) {
//                    return Integer.compare(seat1.getAxisX(), seat2.getAxisX());
//                }
//
//                return axisYComparison;
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        // 2. 포인터로 전체 리스트 순회
        AxisY currentRow = mutableSeats.getFirst().getAxisY();  // 첫 번째 좌석의 행
        int prevX = mutableSeats.getFirst().getAxisX();  // 첫 번째 좌석의 열

        for (int i = 1; i < mutableSeats.size(); i++) {
            Seat currentSeat = mutableSeats.get(i);

            // 같은 행에 연속된 좌석인지 확인
            if (currentSeat.getAxisY().equals(currentRow)) {
                // X값이 1만큼 증가하는지 확인
                if (currentSeat.getAxisX() != prevX + 1) {
                    isConsecutive = false;
                    break;
                } else {
                    currentRow = currentSeat.getAxisY();
                    prevX = currentSeat.getAxisX();
                    isConsecutive = true;
                }
            } else {
                currentRow = currentSeat.getAxisY();
                prevX = currentSeat.getAxisX();
                isConsecutive = false;
            }
        }

        if (!isConsecutive) {
            List<String> seatNumbers = mutableSeats.stream().map(Seat::getSeatNumber).toList();
            throw new ApplicationException(
                    ErrorCode.UNABLE_TO_RESERVE,
                    "seats are not consecutive - %s".formatted(seatNumbers));
        }
    }

}
