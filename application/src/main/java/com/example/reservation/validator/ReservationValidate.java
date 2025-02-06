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

//        validateAndReserveSeatsInRedis(screening.getId(), request.getSeatIds());

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
//        return new Seats(seatRepository.findAllByIdsWithLock(seatsIds));
        return new Seats(seatRepository.findAllById(seatsIds));
    }

//    private void validateAndReserveSeatsInRedis(Long screeningId, List<Long> seatIds) {
//        String redisKey = "screening:" + screeningId + ":seats";
//
//        // Lua 스크립트: 좌석 상태를 확인하고, 예약되지 않은 경우 예약 상태로 변경
//        String luaScript =
//                "for i, seatId in ipairs(ARGV) do " +
//                        "   if redis.call('HGET', KEYS[1], seatId) == 'true' then " + // 이미 예약된 좌석 확인
//                        "       return 0; " +
//                        "   end; " +
//                        "end; " +
//                        "for i, seatId in ipairs(ARGV) do " +
//                        "   redis.call('HSET', KEYS[1], seatId, 'true'); " + // 좌석 상태를 예약으로 변경
//                        "end; " +
//                        "return 1;";
//
//        // Redisson의 Lua 실행 API 호출
//        Long result = redissonClient.getScript().eval(
//                RScript.Mode.READ_WRITE,
//                luaScript,
//                RScript.ReturnType.INTEGER,
//                List.of(redisKey), // Redis 키
//                seatIds.stream().map(String::valueOf).toArray() // ARGV: 좌석 ID 리스트
//        );
//
//        // Lua 스크립트 결과가 0이면 좌석 예약 실패 처리
//        if (result == 0) {
//            throw new IllegalArgumentException("이미 예매된 좌석입니다.");
//        }
//    }
}
