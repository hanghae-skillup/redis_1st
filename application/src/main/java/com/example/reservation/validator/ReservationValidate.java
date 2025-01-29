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
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationValidate {

    private final MemberRepository memberRepository;
    private final ScreeningRepository screeningRepository;
    private final SeatRepository seatRepository;
    private final ReservedSeatRepository reservedSeatRepository;

    private final RedissonClient redissonClient;

    public ReservationValidationResult validate(ReservationServiceRequest request) {

        if (request.isMemberIdNull()) {
            throw new IllegalArgumentException("영화 예매시 로그인이 필요합니다.");
        }
        if (request.isScreeningIdNull()) {
            throw new IllegalArgumentException("예매할 상영시간을 선택해주세요.");
        }

        Member member = getMember(request.getMemberId());
        Screening screening = getScreening(request.getScreeningId());
        Seats seats = getSeats(request.getSeatIds());

        if (seats.isSeatMatch(request.getSeatIds())) {
            throw new IllegalArgumentException("좌석 정보가 일치하지 않습니다.");
        }

        List<ReservedSeat> prevReservedSeats = getReservedSeats(member, screening);

        if (seats.isTotalSeatCountExceeding(prevReservedSeats)) {
            throw new IllegalArgumentException("하나의 상영시간에 5좌석이상 예매할 수 없습니다");
        }

        validateAndReserveSeatsInRedis(screening.getId(), request.getSeatIds());

//        List<ReservedSeat> existingReservations = reservedSeatRepository.findByScreeningAndSeats(screening, seats.getSeats());
//        if (!existingReservations.isEmpty()) {
//            throw new IllegalArgumentException("이미 예매된 좌석입니다.");
//        }

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
        return memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("회원 정보가 없습니다."));
    }

    private Screening getScreening(Long screeningId) {
        return screeningRepository.findById(screeningId)
                .orElseThrow(() -> new IllegalArgumentException("상영 정보가 없습니다."));
    }

    private Seats getSeats(List<Long> seatsIds) {
//        return new Seats(seatRepository.findAllByIdsWithLock(seatsIds));
        return new Seats(seatRepository.findAllById(seatsIds));
    }

    private void validateAndReserveSeatsInRedis(Long screeningId, List<Long> seatIds) {
        String redisKey = "screening:" + screeningId + ":seats";

        // Lua 스크립트: 좌석 상태를 확인하고, 예약되지 않은 경우 예약 상태로 변경
        String luaScript =
                "for i, seatId in ipairs(ARGV) do " +
                        "   if redis.call('HGET', KEYS[1], seatId) == 'true' then " + // 이미 예약된 좌석 확인
                        "       return 0; " +
                        "   end; " +
                        "end; " +
                        "for i, seatId in ipairs(ARGV) do " +
                        "   redis.call('HSET', KEYS[1], seatId, 'true'); " + // 좌석 상태를 예약으로 변경
                        "end; " +
                        "return 1;";

        // Redisson의 Lua 실행 API 호출
        Long result = redissonClient.getScript().eval(
                RScript.Mode.READ_WRITE,
                luaScript,
                RScript.ReturnType.INTEGER,
                List.of(redisKey), // Redis 키
                seatIds.stream().map(String::valueOf).toArray() // ARGV: 좌석 ID 리스트
        );

        // Lua 스크립트 결과가 0이면 좌석 예약 실패 처리
        if (result == 0) {
            throw new IllegalArgumentException("이미 예매된 좌석입니다.");
        }
    }
}
