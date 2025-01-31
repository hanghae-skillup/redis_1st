package com.example.api.reservation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
@AllArgsConstructor
public class ReservationRequestDto {

    @NotNull
    private Long memberId;

    @NotNull
    private Long screeningId;

    @NotNull
    private List<Long> seatIdList;
}
