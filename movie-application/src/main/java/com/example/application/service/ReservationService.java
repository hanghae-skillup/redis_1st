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
import java.util.function.Supplier;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.LockAcquisitionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public ReservationResponseDto create(ReservationRequestDto request) {
        Screening screening = getScreening(request.screeningId());
        Member member = getMember(request.memberId());

        List<ScreeningSeat> requestedSeats = validateReservationConstraints(screening, member, request.seatIds());

        // 함수형 분산 락을 특정 메서드에 적용
        String lockKey = "seat_reservation:" + request.screeningId() + ":" + request.seatIds();
        long waitTime = 2000;
        long leaseTime = 1000;

        try {
            return distributedLockExecutor.executeWithLock(lockKey, waitTime, leaseTime,
                    () -> {
                        Reservation reservation = saveReservationAndSeats(screening, member, requestedSeats);
                        return ReservationResponseDto.fromEntity(reservation);
                    });
        } catch (LockAcquisitionException e) {
            log.warn("[락 실패] 좌석 예약에 대한 락을 획득하지 못함 - Key: {}", lockKey);
            throw new CustomException(ErrorCode.LOCK_ACQUISITION_FAILED, "좌석 예약 요청이 많아 처리가 지연되었습니다. 다시 시도해주세요.");
        }
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
        Reservation reservation = Reservation.create(screening, member);
        reservationRepositoryPort.save(reservation);

        List<ReservedSeat> reservedSeats = new ArrayList<>();

        for (ScreeningSeat screeningSeat : requestedSeats) {
            // 좌석에 Optimistic Lock 적용하면서 예약된 좌석인지 확인
            screeningSeat.reserve(); // 좌석을 예약 상태로 변경
            screeningSeatRepositoryPort.save(screeningSeat); // 버전 변경 유도
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
