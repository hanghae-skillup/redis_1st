package com.example.app.booking.out.persistence.repository;

import com.example.app.booking.out.persistence.entity.BookingEntity;
import com.example.app.config.QuerydslConfig;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;
import com.querydsl.core.types.ExpressionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.stream.Stream;

import static com.example.app.booking.out.persistence.entity.QBookingEntity.bookingEntity;
import static com.navercorp.fixturemonkey.api.instantiator.Instantiator.constructor;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import({QuerydslConfig.class})
public class BookingRepositoryTest {

    private FixtureMonkey fixtureMonkey;

    @Autowired
    private BookingRepository sut;

    @BeforeEach
    void setUp() {
        fixtureMonkey = FixtureMonkey.builder()
                .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
                .plugin(new JakartaValidationPlugin())
                .build();
    }

    @Test
    public void save_findAllBy_테스트() {
        var booking1 = fixtureMonkey.giveMeBuilder(BookingEntity.class)
                .instantiate(constructor()
                        .parameter(long.class)
                        .parameter(long.class)
                        .parameter(long.class, "movieId")
                        .parameter(long.class)
                        .parameter(LocalDate.class)
                        .parameter(long.class)
                        .parameter(int.class))
                .set("movieId", 1L)
                .sampleList(3);

        var booking2 = fixtureMonkey.giveMeBuilder(BookingEntity.class)
                    .instantiate(constructor()
                            .parameter(long.class)
                            .parameter(long.class)
                            .parameter(long.class, "movieId")
                            .parameter(long.class)
                            .parameter(LocalDate.class)
                            .parameter(long.class)
                            .parameter(int.class))
                    .set("movieId", 2L)
                    .sampleList(4);

        sut.saveAll(Stream.concat(booking1.stream(), booking2.stream()).toList());

        var predicate = ExpressionUtils.allOf(bookingEntity.movieId.eq(1L));

        var result = sut.findAllBy(predicate);

        assertEquals(3, result.size());
    }
}
