package com.example.reservation.validator;

import com.example.entity.member.Member;
import com.example.entity.movie.Screening;
import com.example.entity.movie.Seats;
import com.example.entity.reservation.Reservation;
import com.example.entity.reservation.ReservedSeat;
import com.example.repository.member.MemberRepository;
import com.example.repository.movie.ScreeningRepository;
import com.example.repository.movie.SeatRepository;
import com.example.repository.reservation.ReservedSeatRepository;
import com.example.reservation.request.ReservationServiceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationValidate {

    private final MemberRepository memberRepository;
    private final ScreeningRepository screeningRepository;
    private final SeatRepository seatRepository;
    private final ReservedSeatRepository reservedSeatRepository;

    public Reservation validate(ReservationServiceRequest request) {
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

        List<ReservedSeat> existingReservations = reservedSeatRepository.findByScreeningAndSeats(screening, seats.getSeats());
        if (!existingReservations.isEmpty()) {
            throw new IllegalArgumentException("이미 예매된 좌석입니다.");
        }

        Reservation reservation = Reservation.builder()
                .member(member)
                .screening(screening)
                .build();

        reservation.reservation(seats);

        List<ReservedSeat> reservedSeats = seats.getSeats().stream()
                .map(seat -> new ReservedSeat(reservation, seat))
                .toList();
        reservation.addReservedSeat(reservedSeats);

        return reservation;
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
        return new Seats(seatRepository.findAllById(seatsIds));
    }

}
