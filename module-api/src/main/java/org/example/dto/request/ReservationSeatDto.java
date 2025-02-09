package org.example.dto.request;

import jakarta.validation.constraints.NotNull;
import org.example.annotaion.ValidEnum;
import org.example.domain.seat.Col;
import org.example.domain.seat.Row;

public record ReservationSeatDto(
        @NotNull
        @ValidEnum(enumClass = Row.class, message = "올바른 행 이름을 입력해주세요.")
        String row,
        @NotNull
        @ValidEnum(enumClass = Col.class, message = "올바른 열 이름을 입력해주세요.")
        String col
) {}
