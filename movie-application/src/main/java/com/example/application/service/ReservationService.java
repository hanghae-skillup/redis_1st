package com.example.application.service;

import com.example.application.dto.request.ReservationRequestDto;
import com.example.application.dto.response.ReservationResponseDto;
import com.example.application.lock.DistributedLockExecutor;
import com.example.application.port.in.MessageServicePort;
import com.example.application.port.in.ReservationServicePort;
import com.example.application.port.out.MemberRepositoryPort;
import com.example.application.port.out.ReservationRepositoryPort;
import com.example.application.port.out.ReservationValidationPort;
import com.example.application.port.out.ReservedSeatRepositoryPort;
import com.example.application.port.out.ScreeningRepositoryPort;
import com.example.application.port.out.ScreeningSeatRepositoryPort;
import com.example.domain.exception.CustomException;
import com.example.domain.exception.ErrorCode;
import com.example.domain.model.entity.Member;
import com.example.domain.model.entity.Reservation;
import com.example.domain.model.entity.ReservedSeat;
import com.example.domain.model.entity.Screening;
import com.example.domain.model.entity.ScreeningSeat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReservationService implements ReservationServicePort {

    private final ReservationRepositoryPort reservationRepositoryPort;
    private final MemberRepositoryPort memberRepositoryPort;
    private final ScreeningRepositoryPort screeningRepositoryPort;
    private final ScreeningSeatRepositoryPort screeningSeatRepositoryPort;
    private final ReservedSeatRepositoryPort reservedSeatRepositoryPort;

    private final ReservationValidationPort reservationValidationPort;

    private final MessageServicePort messageServicePort;
    private final DistributedLockExecutor distributedLockExecutor;
    private final TransactionTemplate transactionTemplate;

    @Override
    public ReservationResponseDto create(ReservationRequestDto request) {
        Screening screening = getScreening(request.screeningId());
        Member member = getMember(request.memberId());

        List<ScreeningSeat> requestedSeats = validateReservationConstraints(screening, member, request.seatIds());

        // 함수형 분산 락을 특정 메서드에 적용
        String lockKey = "seat_reservation:" + request.screeningId();
        long waitTime = 7_000; // 락 획득까지 대기할 시간
        long leaseTime = 5_000; // STW 발생 시 대기를 고려해 충분한 시간으로 설정

        Reservation reservation = null;
        try {
            reservation = distributedLockExecutor.executeWithLock(lockKey, waitTime, leaseTime,
                    () -> transactionTemplate.execute(status ->
                    saveReservationAndSeats(screening, member, requestedSeats)
                    )
            );
        } catch (ObjectOptimisticLockingFailureException e) {
            log.warn("[낙관락 예외 발생] 좌석 예약 실패 - 사용자 {}, {}", member.getId(), e.getMessage());
        }

        // 예약 실패 시 예외 처리
        if (reservation == null) {
            throw new CustomException(ErrorCode.RESERVATION_REQUEST_FAILED);
        }

        // 예약 성공 시 알림 전송
        sendReservationConfirmation(reservation.getMember(), requestedSeats, screening);
        log.info("[예약 성공] 사용자명: {}, 좌석: {}", reservation.getMember().getName(),
                reservation.getReservedSeats().stream()
                        .map(reservedSeat -> reservedSeat.getScreeningSeat().getSeat().getSeatNumber().toString())
                        .collect(Collectors.joining(",")));
        return ReservationResponseDto.fromEntity(reservation);
    }

    /** 상영 정보 조회 */
    private Screening getScreening(Long screeningId) {
        return screeningRepositoryPort.findById(screeningId)
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_SCREENING));
    }

    /** 회원 정보 조회 */
    private Member getMember(Long memberId) {
        return memberRepositoryPort.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_MEMBER));
    }

    /** 예약 전 비즈니스 로직 기반으로 요청 값 검증 */
    private List<ScreeningSeat> validateReservationConstraints(Screening screening, Member member, List<Long> seatIds) {
        // 요청된 좌석이 존재하는지 조회
        List<ScreeningSeat> requestedSeats = screeningSeatRepositoryPort.findByScreeningAndSeatIds(screening, seatIds);

        // 요청된 좌석 ID 목록과 조회된 좌석 리스트를 검증 레이어에서 검증
        reservationValidationPort.validateSeatsExist(seatIds, requestedSeats);

        // 해당 회원이 이미 예약한 좌석 수 확인
        int existingReservations = reservationRepositoryPort.countByScreeningAndMember(screening, member);
        reservationValidationPort.validateMaxSeatsPerScreening(existingReservations, requestedSeats.size());

        // 좌석이 연속된 형태인지 검증
        reservationValidationPort.validateSeatsAreConsecutive(requestedSeats.stream().map(ScreeningSeat::getSeat).toList());

        return requestedSeats;
    }

    /** 예약 및 좌석 저장 */
    private Reservation saveReservationAndSeats(Screening screening, Member member, List<ScreeningSeat> requestedSeats) {
        Reservation reservation = Reservation.of(screening, member);
        reservationRepositoryPort.save(reservation);

        List<ReservedSeat> reservedSeats = new ArrayList<>();

        for (ScreeningSeat screeningSeat : requestedSeats) {
            // 좌석에 Optimistic Lock 적용하면서 예약된 좌석인지 확인
            screeningSeat.reserve(); // 좌석을 예약 상태로 변경
            screeningSeatRepositoryPort.saveAndFlush(screeningSeat); // 버전 변경 유도
            ReservedSeat reservedSeat = ReservedSeat.of(reservation, screeningSeat);
            reservedSeats.add(reservedSeat);
        }

        reservedSeatRepositoryPort.saveAll(reservedSeats); // 예약한 좌석 리스트 한 번에 추가
        reservation.addReservedSeats(reservedSeats);  // 예약 정보에 예약한 좌석 리스트 추가

        return reservation;
    }

    /** 예약 완료 후 알림 전송 */
    private void sendReservationConfirmation(Member member, List<ScreeningSeat> requestedSeats, Screening screening) {
        messageServicePort.send(String.format("[예약 완료] 사용자명: %s, 좌석: %s, 영화: %s, 상영관: %s, 시간: %s",
                member.getName(),
                requestedSeats.stream().map(ss -> ss.getSeat().getSeatNumber().toString()).collect(Collectors.joining(", ")),
                screening.getMovie().getTitle(), screening.getTheater().getName(), screening.getStartTime()));
    }

}
