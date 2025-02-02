package com.example.app.booking.out.persistence.repository;

import com.example.app.booking.out.persistence.entity.SeatEntity;
import com.example.app.config.QuerydslConfig;
import com.example.app.movie.type.TheaterSeat;
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
import java.util.HashSet;

import static com.example.app.booking.out.persistence.entity.QSeatEntity.seatEntity;
import static com.navercorp.fixturemonkey.api.instantiator.Instantiator.constructor;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import({QuerydslConfig.class})
public class SeatRepositoryTest {

    @Autowired
    private SeatRepository seatRepository;

    private FixtureMonkey fixtureMonkey;

    @BeforeEach
    void setUp() {
        fixtureMonkey = FixtureMonkey.builder()
                .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
                .plugin(new JakartaValidationPlugin())
                .build();
    }

    @Test
    public void save_findAllBy_테스트() {
        var seats = new HashSet<>(fixtureMonkey.giveMeBuilder(SeatEntity.class)
                .instantiate(constructor()
                        .parameter(long.class)
                        .parameter(long.class)
                        .parameter(long.class)
                        .parameter(long.class)
                        .parameter(long.class)
                        .parameter(LocalDate.class)
                        .parameter(TheaterSeat.class)
                        .parameter(boolean.class, "reserved")
                        .parameter(long.class, "version"))
                .set("reserved", true)
                .set("version", 1L)
                .sampleList(10));

        seatRepository.saveAll(seats);

        var predicate = ExpressionUtils.allOf(seatEntity.reserved.isTrue());

        var result = seatRepository.findAllBy(predicate);

        assertEquals(10, result.size());
    }
}
