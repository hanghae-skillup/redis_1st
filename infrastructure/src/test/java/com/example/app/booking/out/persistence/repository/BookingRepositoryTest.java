package com.example.app.booking.out.persistence.repository;

import com.example.app.booking.out.persistence.entity.BookingEntity;
import com.example.app.config.EmbeddedRedisConfig;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static com.navercorp.fixturemonkey.api.instantiator.Instantiator.constructor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = EmbeddedRedisConfig.class)
@TestPropertySource(properties = "spring.config.location = classpath:application-test.yml")
public class BookingRepositoryTest {

    @Test
    public void findAllBy_테스트() {
        var predicate = FixtureMonkey.create().giveMeOne(Predicate.class);

        var movies = FixtureMonkey.create()
                .giveMeBuilder(BookingEntity.class)
                .instantiate(constructor()
                        .parameter(long.class)
                        .parameter(long.class)
                        .parameter(long.class)
                        .parameter(long.class)
                        .parameter(LocalDate.class)
                        .parameter(long.class)
                        .parameter(int.class))
                .sampleList(10);

        var bookingRepository = mock(BookingRepository.class);
        when(bookingRepository.findAllBy(predicate)).thenReturn(movies);

        var result = bookingRepository.findAllBy(predicate);

        assertEquals(result.size(), 10);
    }
}
