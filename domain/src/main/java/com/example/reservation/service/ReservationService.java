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
import com.example.reservation.dto.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    private ScreeningRepository screeningRepository;

    private ReservationRepository reservationRepository;

    private SeatRepository seatRepository;

    private UserRepository userRepository;

    private MessageService messageService;

    public ReservationResponse reserveSeat(ReservationRequest reservationRequest) throws InterruptedException {
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

        Reservation reservation = new Reservation();
        reservation.setUserId(findUser.getId());
        reservationRepository.save(reservation);

        for (String position : reservationRequest.seats()) {
            Seat findSeat = seatRepository.findByPositionAndScreeningId(position, screening.getId()).orElseThrow(() ->
                    new IllegalArgumentException(SEAT_NOT_FOUND));
            if (findSeat.getReservationId()!=null) throw new IllegalArgumentException(SEAT_ALREADY_BE_MADE_RESERVATION);
            findSeat.reserve(reservation.getId());
            seatRepository.save(findSeat);
        }

        messageService.send(findUser.getId(), reservation.getId());

        return ReservationResponse.builder()
                .reservationId(reservation.getId())
                .build();
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

}
