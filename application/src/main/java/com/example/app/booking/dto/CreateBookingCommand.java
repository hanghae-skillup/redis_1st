package com.example.app.booking.dto;

import com.example.app.booking.domain.Booking;
import com.example.app.movie.type.TheaterSeat;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record CreateBookingCommand(
        Long userId,
        Long movieId,
        Long showtimeId,
        Long theaterId,
        LocalDate bookingDate,
        Set<TheaterSeat> seats
){
    public SearchSeatCommand toSearchSeatCommand() {
        return SearchSeatCommand.builder()
                .movieId(movieId)
                .showtimeId(showtimeId)
                .theaterId(theaterId)
                .bookingDate(bookingDate)
                .seats(seats)
                .build();
    }

    public SearchBookingCommand toSearchBookingCommand() {
        return SearchBookingCommand.builder()
                .userId(userId)
                .movieId(movieId)
                .showtimeId(showtimeId)
                .theaterId(theaterId)
                .bookingDate(bookingDate)
                .build();
    }

    public Booking toBooking() {
        return Booking.builder()
                .userId(userId)
                .movieId(movieId)
                .showtimeId(showtimeId)
                .theaterId(theaterId)
                .bookingDate(bookingDate)
                .totalSeats(seats.size())
                .build();
    }
}
