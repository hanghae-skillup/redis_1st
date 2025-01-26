package com.example.application.reservation.service;

import com.example.application.message.event.ReservationCompletedEvent;
import com.example.application.reservation.dto.ServiceReservationResponseDto;
import com.example.application.reservation.mapper.ApplicationReservationDtoMapper;
import com.example.common.util.BusinessException;
import com.example.common.util.ErrorCode;
import com.example.domain.member.entity.Member;
import com.example.domain.member.repository.MemberRepository;
import com.example.domain.reservation.entity.Reservation;
import com.example.domain.reservation.entity.ReservationSeat;
import com.example.domain.reservation.repository.ReservationRepository;
import com.example.domain.reservation.repository.ReservationSeatRepository;
import com.example.domain.screening.entity.Screening;
import com.example.domain.screening.repository.ScreeningRepository;
import com.example.domain.seat.entity.Seat;
import com.example.domain.seat.repository.SeatRepository;
import com.example.infra.config.RedisLock;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final MemberRepository memberRepository;

    private final ScreeningRepository screeningRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationSeatRepository reservationSeatRepository;
    private final SeatRepository seatRepository;
    private final ApplicationEventPublisher eventPublisher; // 이벤트 발행
    private final RedisLock redisLock;


    @Transactional
    public ServiceReservationResponseDto reserveMovie(Long memberId, Long screeningId, List<Long> seatIdList) {
        String lockKey = "reservation:" + screeningId + ":" + memberId;
        long leaseTime = 2000; // 2초 동안 락 유지
        long waitTime = 2000;  // 2초 동안 락 대기

        return redisLock.executeWithLock(lockKey, leaseTime, waitTime, () -> {
            Member member = memberRepository.findById(memberId).orElseThrow(() -> new BusinessException(ErrorCode.INVALID_INPUT_VALUE));

            Screening screening = screeningRepository.findScreeningWithTheather(screeningId).orElseThrow(() -> new BusinessException(ErrorCode.INVALID_INPUT_VALUE));

            List<Seat> seats = seatRepository.findAvailableSeats(screening.getTheater().getId(), seatIdList);

            if (seats.size() != seatIdList.size()) {
                throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
            }

            validateSeatsAreConsecutive(seats);// 좌석 연속성 검증

            Reservation reservation = Reservation.builder()
                    .member(member)
                    .screening(screening)
                    .reserved_at(LocalDateTime.now())
                    .build();

            reservationRepository.save(reservation);

            seats.forEach(seat -> {
                seat.updateStatus(Seat.Status.RESERVED);

                ReservationSeat reservationSeat = ReservationSeat.builder()
                        .reservation(reservation)
                        .seat(seat)
                        .build();

                reservationSeatRepository.save(reservationSeat);
            });

            eventPublisher.publishEvent(new ReservationCompletedEvent(
                    reservation.getId(),
                    member.getEmail(),
                    screening.getTheater().getName() + "에서 상영 예정입니다."
            ));

            return ApplicationReservationDtoMapper.toServiceReservationResponseDto(seats, screening);
        });


    }

    private void validateSeatsAreConsecutive(List<Seat> seats) {
        // 좌석이 동일한 행에 있는지 확인
        String firstRow = seats.get(0).getSeatRow();
        boolean sameRow = seats.stream()
                .allMatch(seat -> seat.getSeatRow().equals(firstRow));

        if (!sameRow) {
            throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
        }

        // 좌석이 연속된 열에 있는지 확인
        List<String> columns = seats.stream()
                .map(Seat::getSeatCol)
                .sorted()
                .toList();

        for (int i = 0; i < columns.size() - 1; i++) {
            if (Integer.parseInt(columns.get(i)) + 1 != Integer.parseInt(columns.get(i + 1))) {
                throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
            }
        }
    }
}
