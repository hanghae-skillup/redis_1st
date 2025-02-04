package hanghae.application.service;

import hanghae.application.dto.request.ReservationRequest;
import hanghae.application.dto.response.ReservationResponse;
import hanghae.domain.port.ReservationRateLimitChecker;
import hanghae.application.port.ReservationService;
import hanghae.domain.entity.*;
import hanghae.domain.port.*;
import hanghae.infrastructure.common.annotation.DistributedLock;
import hanghae.infrastructure.common.functional.DistributedLockFunction;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;
    private final SeatRepository seatRepository;
    private final ScheduleSeatRepository scheduleSeatRepository;

    private final ReservationRepository reservationRepository;

    private final DistributedLockFunction distributedLockFunction;

    private final ReservationRateLimitChecker reservationRateLimitChecker;


    public static final int MAX_SEATS_PER_RESERVATION = 5;

    @Transactional
    @DistributedLock(
            key = "#{#request.scheduleId}",
            waitSeconds = 1,
            leaseSeconds = 3
    )
    public ReservationResponse reserveSeat(ReservationRequest request) {
        Reservation reservation = initReservation(request);
        List<Seat> seats = getSeats(request);

        List<ScheduleSeat> scheduleSeats = getScheduleSeats(request, seats);
        checkDoubleBooking(scheduleSeats);

        List<ReservationSeat> reservationSeats = toReservationSeats(reservation, seats);
        checkReservationPolicy(reservation, reservationSeats, MAX_SEATS_PER_RESERVATION);

        reservation.setReservationSeats(reservationSeats);
        scheduleSeats.forEach(scheduleSeat -> scheduleSeat.setReserved(true));

        Reservation reservationResult = reservationRepository.reserve(reservation);
        checkReservationRateLimit(request);

        return ReservationResponse.from(reservationResult);
    }

    @Transactional
    public ReservationResponse reserveSeatWithLamda(ReservationRequest request) {
        String key = "#{request.scheduleId}";
        long waitSeconds = 1;
        long leaseSeconds = 3;

        return distributedLockFunction.executeFunctionalLock(
                key,
                waitSeconds,
                leaseSeconds,
                () -> {
                    Reservation reservation = initReservation(request);
                    List<Seat> seats = getSeats(request);

                    List<ScheduleSeat> scheduleSeats = getScheduleSeats(request, seats);
                    checkDoubleBooking(scheduleSeats);

                    List<ReservationSeat> reservationSeats = toReservationSeats(reservation, seats);
                    checkReservationPolicy(reservation, reservationSeats, MAX_SEATS_PER_RESERVATION);

                    reservation.setReservationSeats(reservationSeats);
                    scheduleSeats.forEach(scheduleSeat -> scheduleSeat.setReserved(true));

                    return ReservationResponse.from(reservationRepository.reserve(reservation));
                }
        );
    }

    private Reservation initReservation(ReservationRequest request) {
        Member member = memberRepository.findMemberById(request.memberId())
                .orElseThrow(() -> new IllegalArgumentException("No member found by" + request.memberId()));
        Schedule schedule = scheduleRepository.findScheduleById(request.scheduleId())
                .orElseThrow(() -> new IllegalArgumentException("No schedule found by" + request.scheduleId()));

        return Reservation.of(member, schedule);
    }

    private List<Seat> getSeats(ReservationRequest request) {
        return request.seatNames()
                .stream()
                .map(seatName -> seatRepository.findSeatBySeatNameAndScreenId(seatName, request.screenId())
                        .orElseThrow(() -> new IllegalArgumentException("No seat: " + seatName)))
                .toList();
    }

    private void checkReservationRateLimit(ReservationRequest request) {
        String key = request.memberId() + ":" + request.scheduleId();

        reservationRateLimitChecker.oneReservationPerFiveMinutes(key);
    }

    private List<ScheduleSeat> getScheduleSeats(ReservationRequest request, List<Seat> seats) {
        return seats.stream()
                .map(seat -> scheduleSeatRepository.findScheduleSeatByIds(request.scheduleId(), seat.getSeatId())
                        .orElseThrow(() -> new IllegalArgumentException("No scheduleSeat: " + seat.getSeatId())))
                .toList();
    }

    private List<ReservationSeat> toReservationSeats(Reservation reservation, List<Seat> seats) {
        return seats.stream()
                .map(seat -> ReservationSeat.of(reservation, seat))
                .toList();
    }

    private void checkDoubleBooking(List<ScheduleSeat> scheduleSeats) {
        scheduleSeats.forEach(ScheduleSeat::checkDoubleBooking);
    }

    private void checkReservationPolicy(Reservation reservation, List<ReservationSeat> reservationSeats, int limit) {
        if (isReservationOverLimit(reservationSeats, limit)
                || isTotalReservationOverLimit(reservation, reservationSeats, limit)
        ) {
            throw new IllegalArgumentException("하나의 상영에 대해 한 사람당 최대 5개의 좌석만 예매 가능합니다.");
        }

        if (isAbusing(reservationSeats, limit)) {
            throw new IllegalArgumentException("하나의 상영에 대해 5개의 표를 예매하는 경우 좌석은 붙어있어야 합니다.");
        }
    }

    private boolean isReservationOverLimit(List<ReservationSeat> reservationSeats, int limit) {
        return reservationSeats.size() > limit;
    }

    private boolean isTotalReservationOverLimit(
            Reservation reservation,
            List<ReservationSeat> reservationSeats,
            int limit
    ) {
        List<Reservation> reservations = reservationRepository.findReservationByMemberIdAndScheduleId(
                reservation.getMemberId(),
                reservation.getScheduleId()
        ).orElse(new ArrayList<>());

        if (reservations.isEmpty()) {
            return false;
        }

        if (reservations.size() == 2) {
            throw new IllegalArgumentException("하나의 상영에 대해 최대 2개까지 예약할 수 있습니다.");
        }

        int count = reservations.getFirst().getReservationSeats().size();
        return count + reservationSeats.size() > limit;
    }

    private boolean isAbusing(List<ReservationSeat> reservationSeats, int limit) {
        if (reservationSeats.size() == limit) {
            return !isConsecutiveSeats(reservationSeats, limit);
        } return false;
    }

    private boolean isConsecutiveSeats(List<ReservationSeat> reservationSeats, int limit) {
        List<String> seatNames = getSeatNames(reservationSeats);
        return isSameRow(seatNames);
    }

    private List<String> getSeatNames(List<ReservationSeat> reservationSeats) {
        return reservationSeats.stream()
                .map(reservationSeat -> reservationSeat.getSeat().getSeatName())
                .sorted()
                .toList();
    }

    private boolean isSameRow(List<String> seatNames) {
        char row = seatNames.getFirst().charAt(0);

        return seatNames.stream().allMatch(seatName -> row == seatName.charAt(0));
    }
}
