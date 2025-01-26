package com.example.reservation;

import com.example.entity.member.Member;
import com.example.entity.movie.Screening;
import com.example.entity.movie.Seat;
import com.example.entity.movie.Seats;
import com.example.entity.reservation.Reservation;
import com.example.entity.reservation.ReservedSeat;
import com.example.repository.movie.ScreeningRepository;
import com.example.repository.movie.SeatRepository;
import com.example.repository.reservation.ReservationRepository;
import com.example.repository.reservation.ReservedSeatRepository;
import com.example.repository.member.MemberRepository;
import com.example.reservation.request.ReservationServiceRequest;
import com.example.reservation.response.ReservationServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final SeatRepository seatRepository;
    private final MemberRepository memberRepository;
    private final ScreeningRepository screeningRepository;
    private final ReservationRepository reservationRepository;
    private final ReservedSeatRepository reservedSeatRepository;

    @Transactional
    public ReservationServiceResponse reserve(ReservationServiceRequest request) {
        // 1. 유저 정보 검증
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("회원 정보가 없습니다."));
        // 2. 상영 정보 검증
        Screening screening = screeningRepository.findById(request.getScreeningId())
                .orElseThrow(() -> new IllegalArgumentException("상영 정보가 없습니다."));
        // 3. 좌석 검증
        List<Seat> seats = seatRepository.findAllById(request.getSeatIds());
        if (seats.size() != request.getSeatIds().size()) {
            throw new IllegalArgumentException("좌석 정보가 일치하지 않습니다.");
        }
        // 내가 예약한 좌석 검증

        List<ReservedSeat> allByUserId = reservedSeatRepository.findAllByMemberId(member, screening);
        if (seats.size() + allByUserId.size() > 5) {
            throw new IllegalArgumentException("하나의 사영시간에 5좌석이상 예약할 수 없습니다");
        }

        // 4. 예약된 좌석 검증
        List<ReservedSeat> existingReservations = reservedSeatRepository.findByScreeningAndSeats(screening, seats);

        if (!existingReservations.isEmpty()) {
            throw new IllegalArgumentException("이미 예약된 좌석입니다.");
        }

        Reservation reservation = Reservation.builder()
                .member(member)
                .screening(screening)
                .build();

        reservation.reservation(new Seats(seats));

        List<ReservedSeat> reservedSeats = seats.stream()
                .map(seat -> new ReservedSeat(reservation, seat))
                .toList();
        reservation.addReservedSeat(reservedSeats);

        reservationRepository.save(reservation);
        return ReservationServiceResponse.of(reservation);
    }

}
