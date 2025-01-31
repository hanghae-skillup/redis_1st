package com.example.api.reservation.mapper;

import com.example.api.reservation.dto.ReservationResponseDto;
import com.example.application.reservation.dto.ServiceReservationResponseDto;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ReservationDtoMapper {


    public static ReservationResponseDto toReservationResponseDto(ServiceReservationResponseDto serviceReservationResponseDto) {
        return new ReservationResponseDto(serviceReservationResponseDto.getTheaterName(),
                serviceReservationResponseDto.getStartTime(),
                serviceReservationResponseDto.getEndTime(),
                serviceReservationResponseDto.getReservedSeats()
        );
    }
}
