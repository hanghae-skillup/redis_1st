package com.example.app.booking.out.persistence.mapper;

import com.example.app.booking.domain.Seat;
import com.example.app.booking.out.persistence.entity.SeatEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class SeatMapper {

    public Seat seatEnityToSeat(SeatEntity seatEntity) {
        return Seat.builder()
                .id(seatEntity.getId())
                .bookingId(seatEntity.getBookingId())
                .movieId(seatEntity.getMovieId())
                .showtimeId(seatEntity.getShowtimeId())
                .theaterId(seatEntity.getTheaterId())
                .bookingDate(seatEntity.getBookingDate())
                .reserved(seatEntity.isReserved())
                .theaterSeat(seatEntity.getSeat())
                .build();
    }
}
