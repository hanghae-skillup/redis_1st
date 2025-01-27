package com.example.app.booking.out.persistence.adapter;

import com.example.app.booking.domain.Booking;
import com.example.app.booking.dto.CreateBookingCommand;
import com.example.app.booking.dto.SearchBookingCommand;
import com.example.app.booking.out.persistence.mapper.BookingMapper;
import com.example.app.booking.out.persistence.repository.BookingRepository;
import com.example.app.booking.port.CreateBookingPort;
import com.example.app.booking.port.LoadBookingPort;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.app.booking.out.persistence.entity.QBookingEntity.bookingEntity;
import static java.util.Objects.nonNull;

@Repository
@RequiredArgsConstructor
public class BookingPersistenceAdapter implements CreateBookingPort, LoadBookingPort {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Override
    public List<Booking> loadAllBookings(SearchBookingCommand searchBookingCommand) {
        return bookingRepository.findAllBy(toPredicate(searchBookingCommand))
                .stream()
                .map(bookingMapper::bookingEntityToBooking)
                .toList();
    }

    private Predicate toPredicate(SearchBookingCommand searchBookingCommand) {
        return ExpressionUtils.allOf(
                nonNull(searchBookingCommand.userId()) ?
                        bookingEntity.userId.eq(searchBookingCommand.userId()) : null,
                nonNull(searchBookingCommand.movieId()) ?
                        bookingEntity.movieId.eq(searchBookingCommand.movieId()) : null,
                nonNull(searchBookingCommand.showtimeId()) ?
                        bookingEntity.showtimeId.eq(searchBookingCommand.showtimeId()) : null,
                nonNull(searchBookingCommand.theaterId()) ?
                        bookingEntity.theaterId.eq(searchBookingCommand.theaterId()) : null,
                nonNull(searchBookingCommand.bookingDate()) ?
                        bookingEntity.bookingDate.eq(searchBookingCommand.bookingDate()) : null);
    }

    @Override
    public Booking saveBooking(CreateBookingCommand createBookingCommand) {
        var entity = bookingRepository.save(bookingMapper.bookingToBookingEntity(createBookingCommand.toBooking()));
        return bookingMapper.bookingEntityToBooking(entity);
    }
}
