package com.example.application.port.in;

import com.example.application.dto.request.ReservationRequestDto;
import com.example.application.dto.response.ReservationResponseDto;

public interface ReservationServicePort {
    ReservationResponseDto create(ReservationRequestDto reservationRequestDto);
}
