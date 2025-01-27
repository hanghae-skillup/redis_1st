package hellojpa.service;

import hellojpa.DistributedLock;
import hellojpa.domain.*;
import hellojpa.dto.ReservationCompletedMessageDto;
import hellojpa.dto.ReservationDto;
import hellojpa.exception.SeatReservationException;
import hellojpa.publisher.EventPublisher;
import hellojpa.repository.ReservationRepository;
import hellojpa.repository.ScreeningRepository;
import hellojpa.repository.SeatRepository;
import hellojpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {

    private final UserRepository userRepository;
    private final ScreeningRepository screeningRepository;
    private final SeatRepository seatRepository;
    private final ReservationRepository reservationRepository;
    private final EventPublisher eventPublisher;
    private final RedissonClient redissonClient;

    @Transactional
    //@DistributedLock(key = "#reservationDto.screeningId")
    public void reserveSeats(ReservationDto reservationDto) {

        String lockKey = "lock:screening:" + reservationDto.getScreeningId(); // 락 키
        RLock lock = redissonClient.getLock(lockKey);  // Redisson에서 락 객체 생성

        try {
            // waitTime 동안 락을 시도하고, leaseTime 동안 락을 유지
            boolean isLocked = lock.tryLock(10, 30, TimeUnit.SECONDS);  // 10초 동안 락을 기다리고, 30초 동안 락을 유지

            if (isLocked) {
                try {
                    // 1. 사용자와 상영 정보 조회
                    Users user = userRepository.findById(reservationDto.getUserId())
                            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
                    Screening screening = screeningRepository.findById(reservationDto.getScreeningId())
                            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상영 정보입니다."));

                    // 2. 관람 등급 확인 - 19세 미만은 AGE_19, RESTRICTED 예매 불가능
                    validateAgeRestriction(user, screening);

                    // 3. 좌석 정보 조회 및 검증
                    List<Seat> seats = seatRepository.findByIdWithOptimisticLock(reservationDto.getReservationSeatsId());
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

                } finally {
                    // 락 해제
                    lock.unlock();
                }
            } else {
                log.error("분산 락을 획득할 수 없습니다. 나중에 다시 시도해 주세요.");
                throw new SeatReservationException("분산 락을 획득할 수 없습니다. 나중에 다시 시도해 주세요.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("분산 락 획득 중 오류 발생", e);
            throw new SeatReservationException("분산 락 획득 중 오류 발생", e);
        }
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

        // 좌석을 행별로 그룹화
        Map<String, List<Integer>> groupedByRow = seats.stream()
                .collect(Collectors.groupingBy(
                        Seat::getSeatRow,
                        Collectors.mapping(Seat::getSeatColumn, Collectors.toList())
                ));

        //for (String s : groupedByRow.keySet()) {
        //    log.info("행: {}", s);
        //    log.info("열: {}", groupedByRow.get(s));
        //}

        if(groupedByRow.size() == 1){
            for (Map.Entry<String, List<Integer>> entry : groupedByRow.entrySet()) {
                List<Integer> columns = entry.getValue();
                Collections.sort(columns); // 열 번호 정렬
                checkSeatContinuity(columns, seatCount); // 연속성 및 예약 규칙 확인
            }
        } else if (groupedByRow.size() == 2){
            if(seatCount <= 3){
                throw new SeatReservationException("3좌석 이하 예매시, 좌석은 같은 행에서 연속된 형태로만 예약할 수 있습니다.");
            } else {
                if(seatCount == 4){
                    for (Map.Entry<String, List<Integer>> entry : groupedByRow.entrySet()) {
                        List<Integer> columns = entry.getValue();
                        Collections.sort(columns); // 열 번호 정렬
                        if (columns.size() == 1 || columns.size() == 3){
                            throw new SeatReservationException("4자리는 연속된 4자리 또는 2자리, 2자리 나눠서 예약이 가능합니다.");
                        } else {
                            int count = columns.size();
                            checkSeatContinuity(columns, count); // 연속성 및 예약 규칙 확인
                        }

                    }
                } else if (seatCount == 5){
                    for (Map.Entry<String, List<Integer>> entry : groupedByRow.entrySet()) {
                        List<Integer> columns = entry.getValue();
                        Collections.sort(columns); // 열 번호 정렬
                        if (columns.size() == 1 || columns.size() == 4){
                            throw new SeatReservationException("5자리는 연속된 5자리 또는 2자리, 3자리 나눠서 예약이 가능합니다.");
                        } else {
                            int count = columns.size();
                            checkSeatContinuity(columns, count); // 연속성 및 예약 규칙 확인
                        }

                    }
                }
            }
        }
    }

    private void checkSeatContinuity(List<Integer> columns, int seatCount) {

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