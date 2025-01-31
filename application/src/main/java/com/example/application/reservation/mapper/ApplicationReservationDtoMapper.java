package com.example.application.reservation.mapper;

import com.example.application.reservation.dto.ServiceReservationResponseDto;
import com.example.domain.screening.entity.Screening;
import com.example.domain.seat.entity.Seat;

import java.util.List;
import java.util.stream.Collectors;

public class ApplicationReservationDtoMapper {

    public static ServiceReservationResponseDto toServiceReservationResponseDto(List<Seat> seats, Screening screening){

        List<String> reservedSeats = seats.stream()
                .map(seat -> seat.getSeatRow() + seat.getSeatCol())
                .collect(Collectors.toList());

        return new ServiceReservationResponseDto(
                screening.getTheater().getName(),
                screening.getStartTime().toLocalTime(),
                screening.getEndTime().toLocalTime(),
                reservedSeats);

    }
}

