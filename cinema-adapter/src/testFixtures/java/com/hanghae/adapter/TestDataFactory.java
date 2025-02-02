package com.hanghae.adapter;

import com.hanghae.application.dto.request.MovieReservationRequestDto;
import com.hanghae.domain.model.enums.ScreenSeat;

public class TestDataFactory {

    // 영화 예매 요청 DTO 생성
    public static MovieReservationRequestDto createMovieReservationRequestDto() {
        return new MovieReservationRequestDto(1L, 1L, ScreenSeat.A01, 2);
    }

    public static MovieReservationRequestDto createMovieReservationRequestDto(int seatCount) {
        return new MovieReservationRequestDto(1L, 1L, ScreenSeat.A01, seatCount);
    }

    public static MovieReservationRequestDto createMovieReservationRequestDto(String prefix, int number) {
        return new MovieReservationRequestDto(1L, 1L, ScreenSeat.fromRowAndNumber(prefix, number), 1);
    }
}
