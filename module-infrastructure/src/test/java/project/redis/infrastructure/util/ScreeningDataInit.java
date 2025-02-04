package project.redis.infrastructure.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import project.redis.infrastructure.cinema.entity.CinemaJpaEntity;
import project.redis.infrastructure.cinema.repository.CinemaJpaRepository;
import project.redis.infrastructure.movie.entity.MovieJpaEntity;
import project.redis.infrastructure.movie.repository.MovieJpaRepository;
import project.redis.infrastructure.screening.entity.ScreeningJpaEntity;
import project.redis.infrastructure.screening.repository.ScreeningJpaRepository;

@Component
@RequiredArgsConstructor
public class ScreeningDataInit implements CommandLineRunner {

    private final ScreeningJpaRepository screeningJpaRepository;
    private final MovieJpaRepository movieJpaRepository;
    private final CinemaJpaRepository cinemaJpaRepository;

    private static final Random RANDOM = new Random();

    @Override
    public void run(String... args) throws Exception {
        List<MovieJpaEntity> movies = movieJpaRepository.findAll();
        List<CinemaJpaEntity> cinemas = cinemaJpaRepository.findAll();

        Stream.iterate(0, i -> i + 1)
                .limit(500)
                .parallel()
                .map(index -> {

                    MovieJpaEntity movieJpaEntity = movies.get(RANDOM.nextInt(movies.size()));
                    CinemaJpaEntity cinemaJpaEntity = cinemas.get(RANDOM.nextInt(cinemas.size()));

                    LocalDateTime startTime = generateRandomStartTime();
                    LocalDateTime endTime = startTime.plusMinutes(movieJpaEntity.getRunningMinTime());

                    return ScreeningJpaEntity.builder()
                            .screeningStartTime(startTime)
                            .screeningEndTime(endTime)
                            .movie(movieJpaEntity)
                            .cinema(cinemaJpaEntity)
                            .build();

                })
                .forEach(screeningJpaRepository::save);

    }

    public LocalDateTime generateRandomStartTime() {
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.plusDays(1);
        LocalDate endDate = today.plusDays(20);

        long randomDays = ThreadLocalRandom.current()
                .nextLong(ChronoUnit.DAYS.between(startDate, endDate) + 1);

        return startDate
                .plusDays(randomDays)
                .atTime(new Random().nextInt(18), new Random().nextInt(60));
    }
}
