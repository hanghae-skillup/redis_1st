package com.example.reservation.validator;

import com.example.entity.member.Member;
import com.example.entity.movie.Screening;
import com.example.entity.movie.Seats;
import com.example.entity.reservation.ReservedSeat;
import com.example.repository.member.MemberRepository;
import com.example.repository.movie.ScreeningRepository;
import com.example.repository.movie.SeatRepository;
import com.example.repository.reservation.ReservedSeatRepository;
import com.example.reservation.request.ReservationServiceRequest;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.exception.BusinessError.*;

@Component
@RequiredArgsConstructor
public class ReservationValidate {

    private final MemberRepository memberRepository;
    private final ScreeningRepository screeningRepository;
    private final SeatRepository seatRepository;
    private final ReservedSeatRepository reservedSeatRepository;

    private final RedissonClient redissonClient;

    public ReservationValidationResult validate(ReservationServiceRequest request) {

        Member member = getMember(request.getMemberId());
        Screening screening = getScreening(request.getScreeningId());
        Seats seats = getSeats(request.getSeatIds());

        if (!seats.isContinuousSeat()) {
            throw RESERVATION_SEAT_CONTINUOUS_ERROR.exception();
        }

        if (seats.isSizeExceedingLimit()) {
            throw RESERVATION_SEAT_MAX_SIZE_ERROR.exception();
        }

        if (seats.isSeatMatch(request.getSeatIds())) {
            throw RESERVATION_SEAT_NOT_MATCH_ERROR.exception();
        }

        List<ReservedSeat> prevReservedSeats = getReservedSeats(member, screening);

        if (seats.isTotalSeatCountExceeding(prevReservedSeats)) {
            throw RESERVATION_SEAT_TOTAL_COUNT_ERROR.exception();
        }

        List<ReservedSeat> existingReservations = reservedSeatRepository.findByScreeningAndSeats(screening, seats.getSeats());
        if (!existingReservations.isEmpty()) {
            throw RESERVATION_EXIST_ERROR.exception();
        }

        return ReservationValidationResult.builder()
                .member(member)
                .screening(screening)
                .seats(seats)
                .build();
    }

    private List<ReservedSeat> getReservedSeats(Member member, Screening screening) {
        return reservedSeatRepository.findAllByMemberId(member, screening);
    }

    private Member getMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(USER_NOT_FOUND_ERROR::exception);
    }

    private Screening getScreening(Long screeningId) {
        return screeningRepository.findById(screeningId)
                .orElseThrow(SCREENING_NOT_FOUND_ERROR::exception);
    }

    private Seats getSeats(List<Long> seatsIds) {
        return new Seats(seatRepository.findAllByIdsWithLock(seatsIds));
    }
}
