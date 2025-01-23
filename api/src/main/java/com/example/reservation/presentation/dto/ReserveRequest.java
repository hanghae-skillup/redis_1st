package com.example.reservation.presentation.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ReserveRequest(@NotNull Long userId, @NotNull @NotEmpty List<@NotEmpty String> seatList) {
}
