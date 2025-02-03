package hellojpa.service;

import hellojpa.domain.*;
import hellojpa.dto.ReservationCompletedMessageDto;
import hellojpa.dto.ReservationRequestDto;
import hellojpa.exception.SeatReservationException;
import hellojpa.publisher.EventPublisher;
import hellojpa.repository.ReservationRepository;
import hellojpa.repository.ScreeningRepository;
import hellojpa.repository.SeatRepository;
import hellojpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationTransactionalService {

    private final UserRepository userRepository;
    private final ScreeningRepository screeningRepository;
    private final SeatRepository seatRepository;
    private final ReservationRepository reservationRepository;
    private final EventPublisher eventPublisher;

    @Transactional
    public void reservationProcess(ReservationRequestDto reservationRequestDto) {
        // 1. 사용자와 상영 정보 조회
        Users user = userRepository.findById(reservationRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        Screening screening = screeningRepository.findById(reservationRequestDto.getScreeningId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상영 정보입니다."));

        // 2. 관람 등급 확인 - 19세 미만은 AGE_19, RESTRICTED 예매 불가능
        validateAgeRestriction(user, screening);

        // 3. 좌석 정보 조회 및 검증
        List<Seat> seats = seatRepository.findByIdWithOptimisticLock(reservationRequestDto.getReservationSeatsId());
        validateSeats(seats);

        // 4. 좌석 예약 가능 여부 검증 - 예매된 좌석 예매 불가능
        validateSeatAvailability(seats, screening);

        // 5. 예약 저장
        Reservation reservation = new Reservation(user, screening);
        reservationRepository.save(reservation);

        // 6. 좌석에 예약 정보를 설정
        for (Seat seat : seats) {
            seat.saveReservation(reservation);  // Seat에 예약 정보를 설정
            seatRepository.save(seat);  // Seat 정보 업데이트 (reservation_id가 설정됨)
        }

        // 7. 메시지
        eventPublisher.publish(new ReservationCompletedMessageDto(user.getId(), "영화 제목: " + screening.getMovie().getTitle() +
                " 상영관: " + screening.getTheater().getName() + " 상영 시작 시간: " + screening.getStartTime() +
                " 상영 끝나는 시간: " + screening.getStartTime().plusMinutes(screening.getMovie().getRunningTime()) +
                " 선택한 좌석: " + seats.stream()
                .map(seat -> seat.getSeatRow() + seat.getSeatColumn()) // 행과 열을 결합
                .collect(Collectors.toList()) + "좌석 예약이 완료되었습니다."));
    }

    private void validateAgeRestriction(Users user, Screening screening) {
        VideoRating rating = screening.getMovie().getRating();
        if ((rating == VideoRating.AGE_19 || rating == VideoRating.RESTRICTED) && user.getAge() < 19) {
            throw new SeatReservationException("해당 영화는 나이 제한으로 예매할 수 없습니다.");
        }
    }

    private void validateSeats(List<Seat> seats) {
        int seatCount = seats.size();
        if (seatCount > 5) {
            throw new SeatReservationException("한 번에 최대 5개 좌석만 예약할 수 있습니다.");
        }

        // 행별 좌석을 자동 정렬하여 저장
        TreeMap<String, List<Integer>> seatMap = new TreeMap<>();
        for (Seat seat : seats) {
            seatMap.computeIfAbsent(seat.getSeatRow(), k -> new ArrayList<>()).add(seat.getSeatColumn());
        }

        //for (String s : groupedByRow.keySet()) {
        //    log.info("행: {}", s);
        //    log.info("열: {}", groupedByRow.get(s));
        //}

        // 예매 규칙 검증
        if (seatMap.size() == 1) {
            // 같은 행에서 연속된지 확인
            List<Integer> columns = seatMap.firstEntry().getValue();
            checkSeatContinuity(columns);
        } else if (seatMap.size() == 2) {
            // 4자리 → (2,2) 조합인지 확인 || 5자리 → (2,3) 조합인지 확인
            List<Integer> firstRow = seatMap.firstEntry().getValue();
            List<Integer> secondRow = seatMap.lastEntry().getValue();

            if (!((firstRow.size() == 2 && secondRow.size() == 2 && seatCount == 4) ||
                    (firstRow.size() == 2 && secondRow.size() == 3 && seatCount == 5) ||
                    (firstRow.size() == 3 && secondRow.size() == 2 && seatCount == 5))) {
                throw new SeatReservationException("4자리는 (2,2) 또는 연속 4자리, 5자리는 (2,3) 또는 연속 5자리로만 예약할 수 있습니다.");
            }
        } else {
            throw new SeatReservationException("좌석은 최대 2개 행에서만 예약 가능합니다.");
        }
    }

    private void checkSeatContinuity(List<Integer> columns) {

        // 연속성 확인
        for (int i = 0; i < columns.size() - 1; i++) {
            if (columns.get(i) + 1 != columns.get(i + 1)) {
                throw new SeatReservationException("좌석은 같은 행에서 연속된 형태로만 예약할 수 있습니다.");
            }
        }
    }

    private void validateSeatAvailability(List<Seat> requestedSeats, Screening screening) {

        // 상영 시간표와 좌석 정보를 기준으로 이미 예약된 좌석 조회
        List<Seat> reservedSeats = reservationRepository.findReservedSeatsByScreeningId(screening.getId());

        // 요청 좌석 중 이미 예약된 좌석이 있는지 확인
        List<Seat> unavailableSeats = requestedSeats.stream()
                .filter(reservedSeats::contains)
                .collect(Collectors.toList());

        if (!unavailableSeats.isEmpty()) {
            throw new SeatReservationException("이미 예약된 좌석이 포함되어 있습니다: " +
                    unavailableSeats.stream()
                            .map(seat -> seat.getSeatRow() + seat.getSeatColumn())
                            .collect(Collectors.joining(", ")));
        }
    }

}
