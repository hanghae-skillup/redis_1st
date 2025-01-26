package com.example.api;


import com.example.application.reservation.service.ReservationService;
import com.example.domain.member.entity.Member;
import com.example.domain.member.repository.MemberRepository;
import com.example.domain.movie.entity.Movie;
import com.example.domain.movie.repository.MovieRepository;
import com.example.domain.reservation.entity.Reservation;
import com.example.domain.reservation.repository.ReservationRepository;
import com.example.domain.reservation.repository.ReservationSeatRepository;
import com.example.domain.screening.entity.Screening;
import com.example.domain.screening.repository.ScreeningRepository;
import com.example.domain.seat.entity.Seat;
import com.example.domain.seat.repository.SeatRepository;
import com.example.domain.theater.entity.Theater;
import com.example.domain.theater.repository.TheaterRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class ReservationConcurrencyTest {


    @Autowired
    private ReservationService reservationService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationSeatRepository reservationSeatRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private MovieRepository movieRepository;


    private Member testMember;
    private Movie testMovie;

    private Theater testTheater;

    private Screening testScreening;

    private List<Seat> testSeats;

    @BeforeEach
    void setUp() {
        // 1. 테스트용 영화 생성
        testMovie = movieRepository.save(Movie.builder()
                .title("Movie1")
                .genre(Movie.Genre.SF)
                .releaseDate(LocalDateTime.now().minusDays(1))
                .runtimeMinutes(120).thumbnail("thumbail.jpg")
                .rating(Movie.Rating.AGE_12)
                .build()
        );

        // 2. 테스트용 회원 생성
       testMember = memberRepository.save(Member.builder()
                .name("Test User")
                .email("test@example.com")
                .phoneNumber("010-1234-5678")
                .build()
        );

        // 3. 테스트용 상영관 생성
        testTheater = theaterRepository.save(Theater.builder()
                .name("Test Theater")
                .build()
        );

        // 4. 테스트용 상영 정보 생성
        testScreening = screeningRepository.save(Screening.builder()
                .movie(testMovie)
                .theater(testTheater)
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusHours(2))
                .build());

        // 5. 테스트용 좌석 생성
        testSeats = seatRepository.saveAll(Arrays.asList(
                Seat.builder().seatRow("A").seatCol("1").theater(testTheater).status(Seat.Status.AVAILABLE).build(),
                Seat.builder().seatRow("A").seatCol("2").theater(testTheater).status(Seat.Status.AVAILABLE).build(),
                Seat.builder().seatRow("A").seatCol("3").theater(testTheater).status(Seat.Status.AVAILABLE).build()
        ));
    }

    @AfterEach
    void cleanUp() {
        reservationSeatRepository.deleteAll();
        reservationRepository.deleteAll();
        seatRepository.deleteAll();
        screeningRepository.deleteAll();
        theaterRepository.deleteAll();
        movieRepository.deleteAll();
        memberRepository.deleteAll();
    }


    @Test
    @DisplayName("동시성 테스트")
    void reservationConcurrencyTest() throws InterruptedException {
        int threadCount = 100;

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);


        for (int i = 0; i < threadCount; i++) {
            executor.execute(() -> {
                try {
                    reservationService.reserveMovie(testMember.getId(), testScreening.getId(), testSeats.stream().map(Seat::getId).toList());
                } catch (Exception e) {
                    System.out.println("Thread: " + Thread.currentThread().getName() + " - " + e.getMessage());
                } finally {
                    latch.countDown(); // 작업 완료 후 카운트 감소
                }
            });
        }

        latch.await();
        executor.shutdown();

        //좌석 상태 검증
        List<Seat> seats = seatRepository.findAllById(testSeats.stream().map(Seat::getId).toList());
        seats.forEach(seat -> assertEquals(Seat.Status.RESERVED, seat.getStatus()));

        List<Reservation> reservations = reservationRepository.findAll();
        assertEquals(1, reservations.size());
    }


}
