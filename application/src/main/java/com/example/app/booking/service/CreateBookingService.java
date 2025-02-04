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
        // 유저 확인
        checkValidUser(createBookingCommand.userId());

        // 연속된 row 체크
        TheaterSeat.checkSeatsInSequence(createBookingCommand.seats());

        // 기존 예약 조회
        var existingBookingIds = loadBookingPort.loadAllBookings(createBookingCommand.toSearchBookingCommand())
                .stream()
                .map(Booking::id)
                .toList();

        // 기존 예약의 자리 조회
        var existingSeats = loadSeatPort.loadAllSeatsByBookingIds(existingBookingIds);

        // 요청한 자리 + 이미 예약한 자리 = 5개 넘는지 체크
        checkLimitMaxSeats(createBookingCommand.seats().size() + existingSeats.size());

        // booking 생성
        var booking = createBookingPort.saveBooking(createBookingCommand);

        return distributedLockService.executeWithLockAndReturn(() -> {
            var requestSeats = loadSeatPort.loadAllSeats(createBookingCommand.toSearchSeatCommand());

            // 요청한 자리 예약 가능 여부 체크
            Seat.checkSeatsAvailable(requestSeats);

            // 요청한 자리들 업데이트
            var requestSeatIds = requestSeats.stream().map(Seat::id).toList();
            updateSeatPort.updateAllSeats(requestSeatIds, booking.id());

            return booking;
        }, lockKey, 1L, 3L);
    }

    private void checkLimitMaxSeats(final int totalSeat) {
        if (totalSeat > MAX_SEATS) {
            throw new APIException(OVER_MAX_LIMIT_SEATS);
        }
    }

    private void checkValidUser(final long userId) {
        log.info(">>>>>> Checking userId : {}", userId);
        /* pseudo code
        * try {
        *   var user = userApi.getUser(userId);
        *   if (user == null) { throw new APIException(NOT_VALID_USER); }
        * } catch (Exception e) {
        *   throw new APIException(SERVICE_NETWORK_ERROR);
        * }
        * */
    }
}
