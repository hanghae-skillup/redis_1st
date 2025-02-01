package com.example.app.movie.out.persistence;

import com.example.app.config.EmbeddedRedisConfig;
import com.example.app.movie.out.persistence.entity.MovieEntity;
import com.example.app.movie.out.persistence.repository.MovieRepository;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = EmbeddedRedisConfig.class)
@TestPropertySource(properties = "spring.config.location = classpath:application-test.yml")
public class MovieRepositoryTest {

    @Test
    public void findAllBy_테스트() {
        FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
                .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
                .plugin(new JakartaValidationPlugin())
                .build();

        var predicate = fixtureMonkey.giveMeOne(Predicate.class);

        var movies = fixtureMonkey
                .giveMeBuilder(MovieEntity.class)
                .sampleList(10);

        var movieRepository = mock(MovieRepository.class);
        when(movieRepository.findAllBy(predicate)).thenReturn(movies);

        var result = movieRepository.findAllBy(predicate);

        assertEquals(result.size(), 10);
    }
}
