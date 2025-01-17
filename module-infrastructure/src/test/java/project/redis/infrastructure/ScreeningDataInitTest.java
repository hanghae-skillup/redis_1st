package project.redis.infrastructure;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.redis.infrastructure.cinema.repository.CinemaJpaRepository;
import project.redis.infrastructure.movie.repository.MovieJpaRepository;
import project.redis.infrastructure.screening.repository.ScreeningJpaRepository;

@ExtendWith(MockitoExtension.class)
class ScreeningDataInitTest {

    @Mock
    ScreeningJpaRepository screeningJpaRepository;

    @Mock
    MovieJpaRepository movieJpaRepository;

    @Mock
    CinemaJpaRepository cinemaJpaRepository;

    @InjectMocks
    private ScreeningDataInit screeningDataInit;

    @Test
    void testRandomStartTime() {
        LocalDateTime startTime = screeningDataInit.generateRandomStartTime();
        System.out.println("startTime = " + startTime);
    }
}