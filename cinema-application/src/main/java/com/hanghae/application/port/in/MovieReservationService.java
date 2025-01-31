package com.hanghae.application.port.in;

import com.hanghae.application.dto.ApiResponse;
import com.hanghae.application.dto.MovieReservationRequestDto;

public interface MovieReservationService {
    public ApiResponse<Void> saveMovieReservation(MovieReservationRequestDto requestDto);
}
