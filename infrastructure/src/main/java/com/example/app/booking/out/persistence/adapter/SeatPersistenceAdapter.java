package com.example.app.booking.out.persistence.adapter;

import com.example.app.booking.domain.Seat;
import com.example.app.booking.dto.SearchSeatCommand;
import com.example.app.booking.out.persistence.entity.SeatEntity;
import com.example.app.booking.out.persistence.mapper.SeatMapper;
import com.example.app.booking.out.persistence.repository.SeatRepository;
import com.example.app.booking.port.LoadSeatPort;
import com.example.app.booking.port.UpdateSeatPort;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.app.booking.out.persistence.entity.QSeatEntity.seatEntity;
import static java.util.Objects.nonNull;

@Repository
@RequiredArgsConstructor
public class SeatPersistenceAdapter implements LoadSeatPort, UpdateSeatPort {

    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;

    @Override
    public List<Seat> updateAllSeats(List<Long> seatIds, Long bookingId) {
        var seatEntities = seatRepository.findAllById(seatIds);

        for (SeatEntity seat : seatEntities) {
            seat.occupySeat(bookingId);
        }

        return seatRepository.saveAll(seatEntities)
                .stream()
                .map(seatMapper::seatEnityToSeat)
                .toList();
    }

    @Override
    public List<Seat> loadAllSeatsByBookingIds(List<Long> bookingIds) {
        return seatRepository.findAllByBookingIdIn(bookingIds)
                .stream()
                .map(seatMapper::seatEnityToSeat)
                .toList();
    }

    @Override
    public List<Seat> loadAllSeats(SearchSeatCommand searchSeatCommand) {
        return seatRepository.findAllBy(toPredicate(searchSeatCommand))
                .stream()
                .map(seatMapper::seatEnityToSeat)
                .toList();
    }

    private Predicate toPredicate(SearchSeatCommand searchSeatCommand) {
        return ExpressionUtils.allOf(
                nonNull(searchSeatCommand.movieId()) ? seatEntity.movieId.eq(searchSeatCommand.movieId()) : null,
                nonNull(searchSeatCommand.showtimeId()) ? seatEntity.showtimeId.eq(searchSeatCommand.showtimeId()) : null,
                nonNull(searchSeatCommand.theaterId()) ? seatEntity.theaterId.eq(searchSeatCommand.theaterId()) : null,
                nonNull(searchSeatCommand.bookingDate()) ? seatEntity.bookingDate.eq(searchSeatCommand.bookingDate()) : null,
                searchSeatCommand.seats().isEmpty() ? null : seatEntity.seat.in(searchSeatCommand.seats())
        );
    }
}
