package com.example.reservation.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ReservationRequest(@NotNull Long userId, @NotNull Long screeningId, @NotNull @NotEmpty List<@NotEmpty String> seats){
}
