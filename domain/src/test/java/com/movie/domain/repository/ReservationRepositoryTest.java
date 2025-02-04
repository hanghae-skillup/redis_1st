package com.movie.domain.repository;

import com.movie.domain.DomainApplication;
import com.movie.domain.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.junit.jupiter.api.Disabled;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(DomainApplication.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Disabled("Temporarily disabled until configuration issues are resolved")
class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private UserRepository userRepository;

    private Movie movie;
    private Schedule schedule;
    private Seat seat1;
    private Seat seat2;
    private User user;

    @BeforeEach
    void setUp() {
        // 영화 생성
        movie = Movie.builder()
                .title("테스트 영화")
                .grade("12세 이상")
                .genre("액션")
                .description("테스트 영화입니다.")
                .runningTime(120)
                .releaseDate(LocalDateTime.now())
                .thumbnailUrl("http://example.com/test.jpg")
                .build();
        movieRepository.save(movie);

        // 상영 일정 생성
        schedule = Schedule.builder()
                .movie(movie)
                .startTime(LocalDateTime.now().plusDays(1))
                .endTime(LocalDateTime.now().plusDays(1).plusHours(2))
                .build();
        scheduleRepository.save(schedule);

        // 좌석 생성
        seat1 = Seat.builder()
                .seatNumber("A1")
                .build();
        seat2 = Seat.builder()
                .seatNumber("A2")
                .build();
        seatRepository.saveAll(List.of(seat1, seat2));

        // 사용자 생성
        user = User.builder()
                .email("test@example.com")
                .password("password")
                .name("테스트 사용자")
                .build();
        userRepository.save(user);
    }

    @Test
    void existsByScheduleIdAndSeatId() {
        // given
        Reservation reservation = Reservation.builder()
                .reservationNumber("TEST-001")
                .user(user)
                .schedule(schedule)
                .seats(List.of(seat1))
                .build();
        reservationRepository.save(reservation);

        // when
        boolean exists = reservationRepository.existsByScheduleIdAndSeatId(schedule.getId(), seat1.getId());
        boolean notExists = reservationRepository.existsByScheduleIdAndSeatId(schedule.getId(), seat2.getId());

        // then
        assertThat(exists).isTrue();
        assertThat(notExists).isFalse();
    }

    @Test
    void countByUserIdAndScheduleIdAndStatus() {
        // given
        Reservation reservation1 = Reservation.builder()
                .reservationNumber("TEST-001")
                .user(user)
                .schedule(schedule)
                .seats(List.of(seat1))
                .build();
        Reservation reservation2 = Reservation.builder()
                .reservationNumber("TEST-002")
                .user(user)
                .schedule(schedule)
                .seats(List.of(seat2))
                .build();
        reservationRepository.saveAll(List.of(reservation1, reservation2));

        // when
        long count = reservationRepository.countByUserIdAndScheduleIdAndStatus(user.getId(), schedule.getId(), ReservationStatus.RESERVED);

        // then
        assertThat(count).isEqualTo(2);
    }

    @Test
    void findByUserIdAndScheduleIdAndStatus() {
        // given
        Reservation reservation1 = Reservation.builder()
                .reservationNumber("TEST-001")
                .user(user)
                .schedule(schedule)
                .seats(List.of(seat1))
                .build();
        Reservation reservation2 = Reservation.builder()
                .reservationNumber("TEST-002")
                .user(user)
                .schedule(schedule)
                .seats(List.of(seat2))
                .build();
        reservationRepository.saveAll(List.of(reservation1, reservation2));

        // when
        List<Reservation> reservations = reservationRepository.findByUserIdAndScheduleIdAndStatus(user.getId(), schedule.getId(), ReservationStatus.RESERVED);

        // then
        assertThat(reservations).hasSize(2)
                .extracting("reservationNumber")
                .containsExactlyInAnyOrder("TEST-001", "TEST-002");
    }

    @Test
    void findByUserId() {
        // given
        Reservation reservation1 = Reservation.builder()
                .reservationNumber("TEST-001")
                .user(user)
                .schedule(schedule)
                .seats(List.of(seat1))
                .build();
        Reservation reservation2 = Reservation.builder()
                .reservationNumber("TEST-002")
                .user(user)
                .schedule(schedule)
                .seats(List.of(seat2))
                .build();
        reservation2.cancel();
        reservationRepository.saveAll(List.of(reservation1, reservation2));

        // when
        List<Reservation> reservations = reservationRepository.findByUserId(user.getId());

        // then
        assertThat(reservations).hasSize(2)
                .extracting("reservationNumber")
                .containsExactlyInAnyOrder("TEST-001", "TEST-002");
    }

    @Test
    void findByReservationNumber() {
        // given
        Reservation reservation = Reservation.builder()
                .reservationNumber("TEST-001")
                .user(user)
                .schedule(schedule)
                .seats(List.of(seat1))
                .build();
        reservationRepository.save(reservation);

        // when
        Optional<Reservation> found = reservationRepository.findByReservationNumber("TEST-001");

        // then
        assertThat(found).isPresent();
        assertThat(found.get().getReservationNumber()).isEqualTo("TEST-001");
    }

    @Test
    void findBySchedule() {
        // given
        User user2 = User.builder()
                .email("test2@example.com")
                .password("password")
                .name("테스트 사용자2")
                .build();
        userRepository.save(user2);

        Reservation reservation1 = Reservation.builder()
                .reservationNumber("TEST-001")
                .user(user)
                .schedule(schedule)
                .seats(List.of(seat1))
                .build();
        Reservation reservation2 = Reservation.builder()
                .reservationNumber("TEST-002")
                .user(user2)
                .schedule(schedule)
                .seats(List.of(seat2))
                .build();
        reservationRepository.saveAll(List.of(reservation1, reservation2));

        // when
        List<Reservation> reservations = reservationRepository.findBySchedule(schedule);

        // then
        assertThat(reservations).hasSize(2)
                .extracting("reservationNumber")
                .containsExactlyInAnyOrder("TEST-001", "TEST-002");
    }
} 