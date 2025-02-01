package com.example.app.booking.out.persistence.repository;

import com.example.app.booking.out.persistence.entity.SeatEntity;
import com.example.app.config.EmbeddedRedisConfig;
import com.example.app.movie.type.TheaterSeat;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static com.navercorp.fixturemonkey.api.instantiator.Instantiator.constructor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = EmbeddedRedisConfig.class)
@TestPropertySource(properties = "spring.config.location = classpath:application-test.yml")
public class SeatRepositoryTest {

    @Test
    public void findAllBy_테스트() {
        var predicate = FixtureMonkey.create().giveMeOne(Predicate.class);

        var seats = FixtureMonkey.create()
                .giveMeBuilder(SeatEntity.class)
                .instantiate(constructor()
                        .parameter(long.class)
                        .parameter(long.class)
                        .parameter(long.class)
                        .parameter(long.class)
                        .parameter(long.class)
                        .parameter(LocalDate.class)
                        .parameter(TheaterSeat.class)
                        .parameter(boolean.class)
                        .parameter(long.class))
                .sampleList(10);

        var seatRepository = mock(SeatRepository.class);
        when(seatRepository.findAllBy(predicate)).thenReturn(seats);

        var result = seatRepository.findAllBy(predicate);

        assertEquals(result.size(), 10);
    }
}
