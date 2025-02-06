package com.example.reservation;

import com.example.entity.member.Member;
import com.example.entity.movie.*;
import com.example.entity.reservation.ReservedSeat;
import com.example.exception.BusinessException;
import com.example.repository.member.MemberRepository;
import com.example.repository.movie.MovieRepository;
import com.example.repository.movie.ScreeningRepository;
import com.example.repository.movie.SeatRepository;
import com.example.repository.movie.TheaterRepository;
import com.example.repository.reservation.ReservationRepository;
import com.example.repository.reservation.ReservedSeatRepository;
import com.example.reservation.request.ReservationServiceRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class ReservationServiceTest {

    @Autowired SeatRepository seatRepository;
    @Autowired MovieRepository movieRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired TheaterRepository theaterRepository;
    @Autowired ScreeningRepository screeningRepository;
    @Autowired ReservationRepository reservationRepository;
    @Autowired ReservedSeatRepository reservedSeatRepository;

    @Autowired ReservationService reservationService;

    @AfterEach
    void tearDown() {
        reservedSeatRepository.deleteAll();
        reservationRepository.deleteAll();
        seatRepository.deleteAll();
        screeningRepository.deleteAll();
        theaterRepository.deleteAll();
        movieRepository.deleteAll();
        memberRepository.deleteAll();
    }


    @Test
    @DisplayName("회원정보가 없으면 예외가 던져집니다.")
    void reservation_user_exception() {
        Member member = createUser("kimbro1", "1234", "kimbro1@gamil.com");

        memberRepository.save(member);
        assertThatThrownBy(() -> { reservationService.reserve(new ReservationServiceRequest(2L, 1L, List.of(1L, 2L))); })
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("회원 정보가 없습니다.");
    }

    @Test
    @DisplayName("상영 정보가 없으면 예외가 던져집니다.")
    void reservation_screening_exception() {

        Member member = createUser("kimbro1", "1234", "kimbro1@gamil.com");
        Movie movie = createMovie("히트맨 2", "http://example.com/hitmen.jpg", 120, Genre.ACTION, Rating.ALL, LocalDate.of(2025, 1, 22));
        Theater theater = createTheater("1관");
        List<Seat> seats = createSeats(theater);
        Screening screening = createScreening(movie, theater, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());

        memberRepository.save(member);
        movieRepository.save(movie);
        theaterRepository.save(theater);
        List<Seat> seats1 = seatRepository.saveAll(seats);
        for (Seat seat : seats1) {
            System.out.println(seat.getId());
        }
        screeningRepository.save(screening);

        assertThatThrownBy(() -> { reservationService.reserve(new ReservationServiceRequest(member.getId(), 2L, List.of(1L, 2L))); })
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("상영 정보가 없습니다.");

    }

    @Test
    @DisplayName("좌석 정보가 일치하지 않으면 예외가 던져집니다.")
    void reservation_seat_exception() {
        Member member = createUser("kimbro2", "12341234", "kimbro2@gamil.com");
        Movie movie = createMovie("히트맨 2", "http://example.com/hitmen.jpg", 120, Genre.ACTION, Rating.ALL, LocalDate.of(2025, 1, 22));
        Theater theater = createTheater("1관");
        List<Seat> seats = createSeats(theater);
        Screening screening = createScreening(movie, theater, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());

        memberRepository.save(member);
        movieRepository.save(movie);
        theaterRepository.save(theater);
        seatRepository.saveAll(seats);
        screeningRepository.save(screening);

        assertThatThrownBy(() -> { reservationService.reserve(new ReservationServiceRequest(member.getId(), screening.getId(), List.of(1000L, 10001L))); })
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("좌석 정보가 일치하지 않습니다.");
    }

    @Test
    @DisplayName("하나의 상영에 예매가 5좌석 초과하면 예외가 던져진다.")
    void reservation_seat_max_five_exception() {
        Member member = createUser("kimbro2", "12341234", "kimbro2@gamil.com");
        Movie movie = createMovie("히트맨 2", "http://example.com/hitmen.jpg", 120, Genre.ACTION, Rating.ALL, LocalDate.of(2025, 1, 22));
        Theater theater = createTheater("1관");
        List<Seat> seats = createSeats(theater);
        Screening screening = createScreening(movie, theater, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());

        memberRepository.save(member);
        movieRepository.save(movie);
        theaterRepository.save(theater);
        seatRepository.saveAll(seats);
        screeningRepository.save(screening);
        reservationService.reserve(new ReservationServiceRequest(member.getId(), screening.getId(), List.of(seats.get(0).getId(), seats.get(1).getId())));
        assertThatThrownBy(() -> { reservationService.reserve(new ReservationServiceRequest(member.getId(), screening.getId(), List.of(seats.get(5).getId(), seats.get(6).getId(), seats.get(7).getId(), seats.get(8).getId()))); })
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("하나의 상영시간에 5좌석이상 예매할 수 없습니다");
    }

    @Test
    @DisplayName("이미 예매된 좌석을 예매하면 예외가 던져진다.")
    void reservation_duplication_exception() {
        Member member = createUser("kimbro2", "12341234", "kimbro2@gamil.com");
        Movie movie = createMovie("히트맨 2", "http://example.com/hitmen.jpg", 120, Genre.ACTION, Rating.ALL, LocalDate.of(2025, 1, 22));
        Theater theater = createTheater("1관");
        List<Seat> seats = createSeats(theater);
        Screening screening = createScreening(movie, theater, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());

        memberRepository.save(member);
        movieRepository.save(movie);
        theaterRepository.save(theater);
        seatRepository.saveAll(seats);
        screeningRepository.save(screening);

        reservationService.reserve(new ReservationServiceRequest(member.getId(), screening.getId(), List.of(seats.get(0).getId(), seats.get(1).getId())));

        assertThatThrownBy(() -> {
            reservationService.reserve(new ReservationServiceRequest(member.getId(), screening.getId(), List.of(seats.get(0).getId(), seats.get(1).getId())));
        })
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("이미 예매된 좌석입니다.");
    }

    @Test
    @DisplayName("10명의 회원이 동시에 예매를 할 때 1개의 예매 건만 만들어져야 한다.")
    void reservation_concurrency_test() throws InterruptedException {
        // 데이터 생성
        Member member = createUser("kimbro1", "12341234", "kimbro2@gamil.com");
        Movie movie = createMovie("히트맨 2", "http://example.com/hitmen.jpg", 120, Genre.ACTION, Rating.ALL, LocalDate.of(2025, 1, 22));
        Theater theater = createTheater("1관");
        List<Seat> seats = createSeats(theater);
        Screening screening = createScreening(movie, theater, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());

        Member savedMember = memberRepository.save(member);
        movieRepository.save(movie);
        theaterRepository.save(theater);
        seatRepository.saveAll(seats);
        screeningRepository.save(screening);

        // 테스트 데이터 요청
        ReservationServiceRequest request = ReservationServiceRequest.builder()
                .memberId(savedMember.getId())
                .screeningId(screening.getId())
                .seatIds(List.of(seats.get(0).getId())) // 동일한 좌석 요청
                .build();

        ExecutorService executorService = Executors.newFixedThreadPool(30);
        CountDownLatch latch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                try {
                    reservationService.reserve(request);
                } catch (Exception e) {
                    System.out.println("message " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        // 예약된 좌석 수 검증
        List<ReservedSeat> reservedSeats = reservedSeatRepository.findByScreeningAndSeats(screening, List.of(seats.get(0)));
        assertThat(reservedSeats.size()).isEqualTo(1); // 중복 예약이 없어야 하므로 좌석은 1개만 예약됨
    }

    private static Member createUser(String username, String password, String email) {
        return Member.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();
    }

    private static Movie createMovie(String title, String thumbnailUrl, int runningTime, Genre genre, Rating rating, LocalDate releaseDate) {
        return Movie.builder()
                .title(title)
                .thumbnailUrl(thumbnailUrl)
                .runningTime(runningTime)
                .genre(genre)
                .rating(rating)
                .releaseDate(releaseDate)
                .build();
    }

    private static Screening createScreening(Movie movie, Theater theater, LocalDate screeningAt, LocalDateTime startedAt, LocalDateTime endedAt) {
        return Screening.builder()
                .movie(movie)
                .theater(theater)
                .screeningAt(screeningAt)
                .startedAt(startedAt)
                .endedAt(endedAt)
                .build();
    }

    private static Theater createTheater(String name) {
        return Theater.builder()
                .name(name)
                .build();
    }

    private static List<Seat> createSeats(Theater theater) {
        List<Seat> seats  = new ArrayList<>();
        String[] rows = {"A", "B", "C", "D", "E"};
        for (String row : rows) {
            for (int i = 1; i <= 5; i++) {
                String seatNumber = row + i;
                seats.add(new Seat(seatNumber, theater));
            }
        }
        return seats;
    }
}