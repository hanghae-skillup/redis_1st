package project.redis.domain.screening;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.redis.domain.cinema.Cinema;
import project.redis.domain.movie.Movie;

class ScreeningTest {


    @Test
    void testGenerateScreening() {

        Movie movieMock = Mockito.mock(Movie.class);
        Cinema cinemaMock = Mockito.mock(Cinema.class);
        UUID screeningId = UUID.randomUUID();
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusMinutes(100);

        Screening result = Screening.generateScreening(screeningId, start, end, movieMock, cinemaMock);

        assertThat(result.getScreeningId()).isEqualTo(screeningId);
        assertThat(result.getScreenStartTime()).isEqualTo(start);
        assertThat(result.getScreenEndTime()).isEqualTo(end);
        assertThat(result.getMovie()).isEqualTo(movieMock);
        assertThat(result.getCinema()).isEqualTo(cinemaMock);
    }

    @Test
    void testGenerateScreeningException() {

        Movie movieMock = Mockito.mock(Movie.class);
        UUID screeningId = UUID.randomUUID();
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusMinutes(100);

        Assertions.assertThrows(
                AssertionError.class,
                ()-> Screening.generateScreening(screeningId, start, end, movieMock, null));
    }

}