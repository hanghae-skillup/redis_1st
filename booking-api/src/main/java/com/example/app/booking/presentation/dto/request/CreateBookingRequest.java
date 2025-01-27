package com.example.app.booking.presentation.dto.request;

import com.example.app.booking.dto.CreateBookingCommand;
import com.example.app.movie.type.TheaterSeat;
import com.example.app.common.annotation.ValidEnums;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record CreateBookingRequest(
        @Positive(message = "userId는 0보다 커요")
        Long userId,
        @Positive(message = "movieId는 0보다 커요")
        Long movieId,
        @Positive(message = "showtimeId는 0보다 커요")
        Long showtimeId,
        @Positive(message = "theaterId는 0보다 커요")
        Long theaterId,
        @FutureOrPresent
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate bookingDate,
        @NotNull
        @ValidEnums(enumClass = TheaterSeat.class)
        @Size(min = 1, max = 5, message = "좌석은 최소 1개, 최대 5개 예매 가능해요")
        List<String> seats
) {
        public CreateBookingCommand toCreateBookingCommand() {
                return CreateBookingCommand.builder()
                        .userId(userId)
                        .movieId(movieId)
                        .showtimeId(showtimeId)
                        .theaterId(theaterId)
                        .bookingDate(bookingDate)
                        .seats(seats.stream().map(TheaterSeat::valueOf).collect(Collectors.toSet()))
                        .build();
        }
}
