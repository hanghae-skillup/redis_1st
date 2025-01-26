package com.example.app.booking.service;

import com.example.app.booking.domain.Booking;
import com.example.app.booking.domain.Seat;
import com.example.app.booking.dto.CreateBookingCommand;
import com.example.app.booking.port.CreateBookingPort;
import com.example.app.booking.port.LoadBookingPort;
import com.example.app.booking.port.LoadSeatPort;
import com.example.app.booking.port.UpdateSeatPort;
import com.example.app.common.exception.APIException;
import com.example.app.common.function.DistributedLockService;
import com.example.app.movie.type.TheaterSeat;
import com.example.app.booking.usecase.CreateBookingUseCase;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.example.app.booking.exception.BookingErrorMessage.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateBookingService implements CreateBookingUseCase {

    private final Integer MAX_SEATS = 5;

    private final LoadSeatPort loadSeatPort;
    private final UpdateSeatPort updateSeatPort;
    private final LoadBookingPort loadBookingPort;
    private final CreateBookingPort createBookingPort;

    private final DistributedLockService distributedLockService;

    @Override
    @Transactional
    public Booking createBooking(String lockKey, CreateBookingCommand createBookingCommand) {
        return distributedLockService.executeWithLockAndReturn(() -> {
            // 연속된 row 체크
            checkSeatsInSequence(createBookingCommand.seats());

            // 요청한 자리 예약 가능 여부 체크
            var requestSeats = loadSeatPort.loadAllSeats(createBookingCommand.toSearchSeatCommand());
            checkSeatsAvailable(requestSeats);

            // 기존 예약 조회
            var existingBookingIds = loadBookingPort.loadAllBookings(createBookingCommand.toSearchBookingCommand())
                    .stream()
                    .map(Booking::id)
                    .toList();

            // 기존 예약의 자리 조회
            var existingSeats = loadSeatPort.loadAllSeatsByBookingIds(existingBookingIds);

            // 요청한 자리 + 이미 예약한 자리 = 5개 넘는지 체크
            checkLimitMaxSeats(requestSeats.size() + existingSeats.size());

            // booking 생성
            var booking = createBookingPort.saveBooking(createBookingCommand);

            // 요청한 자리들 업데이트
            var requestSeatIds = requestSeats.stream().map(Seat::id).toList();
            updateSeatPort.updateAllSeats(requestSeatIds, booking.id());

            return booking;
        }, lockKey, 2L, 3L);
    }

    private void checkLimitMaxSeats(Integer totalSeat) {
        if (totalSeat > MAX_SEATS) {
            throw new APIException(OVER_MAX_LIMIT_SEATS);
        }
    }

    private void checkSeatsAvailable(List<Seat> seats) {
        for (Seat seat : seats) {
            if (seat.reserved()) {
                throw new APIException(SEAT_ALREADY_OCCUPIED);
            }
        }
    }

    private void checkSeatsInSequence(Set<TheaterSeat> theaterSeats) {
        String firstRow = TheaterSeat.getRow(theaterSeats.iterator().next());
        for (TheaterSeat theaterSeat : theaterSeats) {
            if (!TheaterSeat.getRow(theaterSeat).equals(firstRow)) {
                throw new APIException(SEAT_ROW_NOT_IN_SEQUENCE);
            }
        }
    }
}
