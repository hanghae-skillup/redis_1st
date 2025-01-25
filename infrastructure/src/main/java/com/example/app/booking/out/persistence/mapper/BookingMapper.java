package com.example.app.booking.out.persistence.mapper;

import com.example.app.booking.domain.Booking;
import com.example.app.booking.out.persistence.entity.BookingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    Booking bookingEntityToBooking(BookingEntity bookingEntity);

    BookingEntity bookingToBookingEntity(Booking booking);
}
