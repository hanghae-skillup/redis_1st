package com.hanghae.application.port.in;

import com.hanghae.application.dto.ApiResponse;
import com.hanghae.application.dto.request.MovieReservationRequestDto;

public interface MovieReservationService {
    public ApiResponse<Void> saveMovieReservation(MovieReservationRequestDto requestDto);
}
