package com.example.api.reservation.controller;

import com.example.api.reservation.dto.ReservationRequestDto;
import com.example.api.reservation.dto.ReservationResponseDto;
import com.example.api.reservation.mapper.ReservationDtoMapper;
import com.example.application.reservation.dto.ServiceReservationResponseDto;
import com.example.application.reservation.service.ReservationService;
import com.example.common.util.dto.DataResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/reservation")
    public ResponseEntity<DataResponse<ReservationResponseDto>> reserveMovie(@RequestBody @Valid ReservationRequestDto reservationRequestDto) {

        ServiceReservationResponseDto serviceReservationResponseDto = reservationService.reserveMovie(
                reservationRequestDto.getMemberId(),
                reservationRequestDto.getScreeningId(),
                reservationRequestDto.getSeatIdList()
        );

        ReservationResponseDto reservationResponseDto = ReservationDtoMapper.toReservationResponseDto(serviceReservationResponseDto);

        return ResponseEntity.ok(DataResponse.response(true, reservationResponseDto));

    }
}
