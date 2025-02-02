package com.example.app.booking.out.persistence.repository;

import com.example.app.booking.out.persistence.entity.SeatEntity;
import com.example.app.config.QuerydslConfig;
import com.example.app.movie.type.TheaterSeat;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;

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
        var seat = fixtureMonkey.giveMeBuilder(SeatEntity.class)
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
                .sample();

        // FIXME: saveAll(SeatEntities)하면 @Version 때문에 update 되는 경우도 생김. 낙관적 락 때문에 목록 조회 테스트 불가.
        seatRepository.save(seat);

        var result = seatRepository.findAllBy(null);

        assertEquals(1, result.size());
    }
}
