package org.example.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ReservationRequestDto(
        @NotNull Long usersId,
        @NotNull Long screenScheduleId,
        @NotNull @Valid List<ReservationSeatDto> reservationSeats
) {}
