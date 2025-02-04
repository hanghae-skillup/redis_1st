package project.redis.infrastructure.integration.reservation;


import static java.util.concurrent.Executors.newFixedThreadPool;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestConstructor;
import project.redis.application.reservation.port.inbound.ReserveCommandParam;
import project.redis.application.reservation.service.ReservationCommandService;
import project.redis.domain.screening.Screening;
import project.redis.domain.seat.Seat;
import project.redis.infrastructure.IntegrationTestConfiguration;
import project.redis.infrastructure.reservation.entity.ReservationJpaEntity;
import project.redis.infrastructure.reservation.entity.ReservationSeatJpaEntity;
import project.redis.infrastructure.reservation.repository.ReservationJpaRepository;
import project.redis.infrastructure.reservation.repository.ReservationSeatJpaRepository;
import project.redis.infrastructure.screening.entity.ScreeningJpaEntity;
import project.redis.infrastructure.screening.mapper.ScreeningInfraMapper;
import project.redis.infrastructure.screening.repository.ScreeningJpaRepository;
import project.redis.infrastructure.seat.mapper.SeatInfraMapper;
import project.redis.infrastructure.seat.respository.SeatJpaRepository;
import project.redis.infrastructure.util.TestContainerSupport;

@Slf4j
@SpringBootTest
@ContextConfiguration(classes = IntegrationTestConfiguration.class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
public class ReservationCommandServiceIntegrationTest extends TestContainerSupport {

    private final ReservationCommandService reservationCommandService;

    private final ReservationJpaRepository reservationJpaRepository;
    private final ReservationSeatJpaRepository reservationSeatJpaRepository;
    private final ScreeningJpaRepository screeningJpaRepository;
    private final SeatJpaRepository seatJpaRepository;

    @AfterEach
    void tearDown() {
        reservationSeatJpaRepository.deleteAll();
        reservationJpaRepository.deleteAll();
    }

    @DisplayName("상영 예약 동시성 테스트")
    @Test
    void testReservationConcurrency() throws InterruptedException {
        LocalDate limitDay = LocalDate.now().plusDays(2);
        List<ScreeningJpaEntity> screenings = screeningJpaRepository.findAllOrderByReleaseDescAndScreenStartTimeAsc(
                limitDay);

        Screening screening = screenings.stream()
                .filter(screeningJpaEntity ->
                        screeningJpaEntity.getScreeningStartTime().isAfter(LocalDateTime.now())
                )
                .findFirst()
                .map(ScreeningInfraMapper::toScreening)
                .get();

        List<UUID> seatIds = seatJpaRepository.findByCinemaId(screening.getCinema().getCinemaId()).stream()
                .filter(seatJpaEntity ->
                        seatJpaEntity.getSeatNumber().contains("A"))
                .map(SeatInfraMapper::toSeat)
                .map(Seat::getSeatId)
                .collect(Collectors.toList());

        int threadCount = 10;
        ExecutorService executorService = newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            executorService.execute(() -> {
                try {

                    ReserveCommandParam param = new ReserveCommandParam(seatIds, screening.getScreeningId(),
                            "user-" + Thread.currentThread().getId());
                    reservationCommandService.reserve(param);

                } catch (Exception e) {
                    log.info("failed to reserve reservation {}", finalI);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        List<ReservationJpaEntity> reservations = reservationJpaRepository.findAll();
        List<ReservationSeatJpaEntity> reservationSeats = reservationSeatJpaRepository.findAll();

        assertThat(reservations.size()).isEqualTo(1);
        assertThat(reservationSeats.size()).isEqualTo(5);
    }


    @DisplayName("상영 예약 테스트")
    @Test
    void testReservation() throws InterruptedException {
        LocalDate limitDay = LocalDate.now().plusDays(2);
        List<ScreeningJpaEntity> screenings = screeningJpaRepository.findAllOrderByReleaseDescAndScreenStartTimeAsc(
                limitDay);

        Screening screening = screenings.stream()
                .filter(screeningJpaEntity ->
                        screeningJpaEntity.getScreeningStartTime().isAfter(LocalDateTime.now())
                )
                .findFirst()
                .map(ScreeningInfraMapper::toScreening)
                .get();

        List<UUID> seatIds = seatJpaRepository.findByCinemaId(screening.getCinema().getCinemaId()).stream()
                .filter(seatJpaEntity ->
                        seatJpaEntity.getSeatNumber().contains("A"))
                .map(SeatInfraMapper::toSeat)
                .map(Seat::getSeatId)
                .collect(Collectors.toList());

        ReserveCommandParam param = new ReserveCommandParam(seatIds, screening.getScreeningId(),
                "user-" + Thread.currentThread().getId());
        reservationCommandService.reserve(param);

        List<ReservationJpaEntity> reservations = reservationJpaRepository.findAll();
        List<ReservationSeatJpaEntity> reservationSeats = reservationSeatJpaRepository.findAll();

        assertThat(reservations.size()).isEqualTo(1);
        assertThat(reservationSeats.size()).isEqualTo(5);
    }
}
