package com.example.reservation.service;

import com.example.jpa.entity.movie.Screening;
import com.example.jpa.entity.reservation.Reservation;
import com.example.jpa.entity.theater.Seat;
import com.example.jpa.entity.user.entity.User;
import com.example.jpa.repository.movie.ScreeningRepository;
import com.example.jpa.repository.movie.SeatRepository;
import com.example.jpa.repository.reservation.ReservationRepository;
import com.example.jpa.repository.user.UserRepository;
import com.example.message.MessageService;
import com.example.reservation.dto.ReservationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

    private static final String USER_NOT_FOUND = "존재하지 않는 유저입니다.";

    private static final String SCREENING_NOT_FOUND = "존재하지 않는 상영 시간표입니다.";

    private static final String SEAT_NOT_FOUND = "존재하지 않는 좌석입니다.";

    private static final String RESERVATION_MUST_BE_UNDER_MAX = "예매 가능한 좌석 수를 초과하였습니다.";

    private static final String SEATS_MUST_BE_ADJACENT = "인접한 좌석으로만 예매 가능합니다.";

    private static final String SEAT_ALREADY_BE_MADE_RESERVATION = "이미 예매된 좌석입니다.";

    private static final Integer MAX_RESERVATION_PER_PERSON = 5;

    private final ScreeningRepository screeningRepository;

    private final ReservationRepository reservationRepository;

    private final SeatRepository seatRepository;

    private final UserRepository userRepository;

    private final MessageService messageService;

    private final RedissonClient redissonClient;

    private final RedisTemplate<String, String> redisTemplate;

    public void reserveSeat(ReservationRequest reservationRequest) throws InterruptedException {
        User findUser = userRepository.findById(reservationRequest.userId()).orElseThrow(() ->
                new IllegalArgumentException(USER_NOT_FOUND));

        Screening screening = screeningRepository.findById(reservationRequest.screeningId()).orElseThrow(() ->
                new IllegalArgumentException(SCREENING_NOT_FOUND));

        if (reservationRequest.seats().size() > MAX_RESERVATION_PER_PERSON) {
            throw new IllegalArgumentException(RESERVATION_MUST_BE_UNDER_MAX);
        }

        if (!areSeatsAdjacent(reservationRequest.seats())) {
            throw new IllegalArgumentException(SEATS_MUST_BE_ADJACENT);
        }

        if (reservationRepository.getReservationCount(findUser.getId(),screening.getId()) + reservationRequest.seats().size() > MAX_RESERVATION_PER_PERSON ) {
            throw new IllegalStateException(RESERVATION_MUST_BE_UNDER_MAX);
        }

        for (String position : reservationRequest.seats()) {
            Seat findSeat = seatRepository.findByPositionAndScreeningId(position, screening.getId()).orElseThrow(() ->
                    new IllegalArgumentException(SEAT_NOT_FOUND));

            if (reservationRepository.existsBySeatIdAndScreeningId(findSeat.getId(), screening.getId()))
                throw new IllegalArgumentException(SEAT_ALREADY_BE_MADE_RESERVATION);

            Reservation reservation = new Reservation();
            reservation.reserve(findUser.getId(), findSeat.getId(), screening.getId());
            reservationRepository.save(reservation);
        }

        messageService.send();
    }

    private boolean areSeatsAdjacent(List<String> seats) {
        for (int i = 0; i < seats.size() - 1; i++) {
            String current = seats.get(i);
            String next = seats.get(i + 1);
            if (!areSeatsNextToEachOther(current, next)) {
                return false;
            }
        }
        return true;
    }

    private boolean areSeatsNextToEachOther(String current, String next) {
        char currentRow = current.charAt(0);
        char nextRow = next.charAt(0);
        int currentSeat = Integer.parseInt(current.substring(1));
        int nextSeat = Integer.parseInt(next.substring(1));

        return currentRow == nextRow && nextSeat == currentSeat + 1;
    }

    public void reserveSeatWithLua(ReservationRequest reservationRequest) throws JsonProcessingException, InterruptedException {
        User findUser = userRepository.findById(reservationRequest.userId())
                .orElseThrow(() -> new IllegalArgumentException(USER_NOT_FOUND));

        Screening screening = screeningRepository.findById(reservationRequest.screeningId())
                .orElseThrow(() -> new IllegalArgumentException(SCREENING_NOT_FOUND));

        if (reservationRequest.seats().size() > MAX_RESERVATION_PER_PERSON) {
            throw new IllegalArgumentException(RESERVATION_MUST_BE_UNDER_MAX);
        }

        if (!areSeatsAdjacent(reservationRequest.seats())) {
            throw new IllegalArgumentException(SEATS_MUST_BE_ADJACENT);
        }

        if (reservationRepository.getReservationCount(findUser.getId(), screening.getId())
                + reservationRequest.seats().size() > MAX_RESERVATION_PER_PERSON) {
            throw new IllegalStateException(RESERVATION_MUST_BE_UNDER_MAX);
        }

        // 버전 키 생성 (상영 ID 기반)
        String versionKey = "screening_version:" + screening.getId();

        // 현재 버전 조회
        String currentVersionStr = redisTemplate.opsForValue().get(versionKey);
        int currentVersion = currentVersionStr == null ? 1 : Integer.parseInt(currentVersionStr);

        // Lua 스크립트 정의
        String luaScript = "local key = KEYS[1]\n" +
                "local expectedVersion = tonumber(ARGV[1])\n" +
                "local userId = ARGV[2]\n" +
                "local screeningId = ARGV[3]\n" +
                "local seats = cjson.decode(ARGV[4])\n" +
                "local expirationTime = tonumber(ARGV[5])\n" +
                "\n" +
                "local currentVersion = redis.call('GET', key)\n" +
                "if not currentVersion then\n" +
                "    currentVersion = 1\n" +
                "    redis.call('SET', key, currentVersion)\n" +
                "else\n" +
                "    currentVersion = tonumber(currentVersion)\n" +
                "end\n" +
                "\n" +
                "if currentVersion ~= expectedVersion then\n" +
                "    return 0\n" +
                "end\n" +
                "\n" +
                "for i, seatPosition in ipairs(seats) do\n" +
                "    local seatKey = 'seat:' .. screeningId .. ':' .. seatPosition\n" +
                "    if redis.call('EXISTS', seatKey) == 1 then\n" +
                "        return {err = 'SEAT_ALREADY_BE_MADE_RESERVATION: ' .. seatPosition}\n" +
                "    end\n" +
                "end\n" +
                "\n" +
                "redis.call('INCR', key)\n" +
                "redis.call('EXPIRE', key, expirationTime)\n" +
                "\n" +
                "for i, seatPosition in ipairs(seats) do\n" +
                "    local seatKey = 'seat:' .. screeningId .. ':' .. seatPosition\n" +
                "    redis.call('SET', seatKey, userId)\n" +
                "end\n" +
                "\n" +
                "return 1";

        // Lua 스크립트 실행
        String seatsJson = new ObjectMapper().writeValueAsString(reservationRequest.seats());
        Long result = redisTemplate.execute(
                (RedisCallback<Long>) connection -> (Long) connection.eval(
                        luaScript.getBytes(),
                        ReturnType.INTEGER,
                        1,  // KEYS 개수
                        versionKey.getBytes(),
                        String.valueOf(currentVersion).getBytes(),
                        findUser.getId().toString().getBytes(),
                        screening.getId().toString().getBytes(),
                        seatsJson.getBytes(),
                        String.valueOf(3600).getBytes()  // 만료 시간: 1시간
                )
        );

        if (result == null || result == 0) {
            throw new IllegalStateException("Version mismatch or seat already reserved.");
        }

        // 좌석 예약이 성공했을 때 DB 저장
        for (String position : reservationRequest.seats()) {
            Seat findSeat = seatRepository.findByPositionAndScreeningId(position, screening.getId())
                    .orElseThrow(() -> new IllegalArgumentException(SEAT_NOT_FOUND));

            Reservation reservation = new Reservation();
            reservation.reserve(findUser.getId(), findSeat.getId(), screening.getId());
            reservationRepository.save(reservation);
        }

        messageService.send();
    }


}
