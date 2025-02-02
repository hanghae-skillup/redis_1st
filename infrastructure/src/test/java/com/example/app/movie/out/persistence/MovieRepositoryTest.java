package com.example.app.movie.out.persistence;

import com.example.app.config.QuerydslConfig;
import com.example.app.movie.out.persistence.entity.MovieEntity;
import com.example.app.movie.out.persistence.entity.MovieTheaterEntity;
import com.example.app.movie.out.persistence.entity.ShowtimeEntity;
import com.example.app.movie.out.persistence.repository.MovieRepository;
import com.example.app.movie.type.MovieGenre;
import com.example.app.movie.type.MovieRating;
import com.example.app.movie.type.MovieStatus;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.navercorp.fixturemonkey.api.type.TypeReference;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;
import com.querydsl.core.types.ExpressionUtils;
import net.jqwik.api.Arbitraries;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Stream;

import static com.example.app.movie.out.persistence.entity.QMovieEntity.movieEntity;
import static com.navercorp.fixturemonkey.api.instantiator.Instantiator.constructor;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import({QuerydslConfig.class})
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

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
        var movies1 = fixtureMonkey.giveMeBuilder(MovieEntity.class)
                .instantiate(constructor()
                        .parameter(long.class)
                        .parameter(String.class, "title")
                        .parameter(String.class)
                        .parameter(MovieStatus.class)
                        .parameter(MovieRating.class)
                        .parameter(MovieGenre.class)
                        .parameter(String.class)
                        .parameter(int.class)
                        .parameter(LocalDate.class)
                        .parameter(new TypeReference<Set<ShowtimeEntity>>() {}, "showtimes")
                        .parameter(new TypeReference<Set<MovieTheaterEntity>>() {}, "movieTheaters"))
                .set("title", "탑건:"+Arbitraries.strings())
                .set("showtimes", null)
                .set("movieTheaters", null)
                .sampleList(2);

        var movies2 = fixtureMonkey.giveMeBuilder(MovieEntity.class)
                .instantiate(constructor()
                        .parameter(long.class)
                        .parameter(String.class, "title")
                        .parameter(String.class)
                        .parameter(MovieStatus.class)
                        .parameter(MovieRating.class)
                        .parameter(MovieGenre.class)
                        .parameter(String.class)
                        .parameter(int.class)
                        .parameter(LocalDate.class)
                        .parameter(new TypeReference<Set<ShowtimeEntity>>() {}, "showtimes")
                        .parameter(new TypeReference<Set<MovieTheaterEntity>>() {}, "movieTheaters"))
                .set("title", "myMovie")
                .set("showtimes", null)
                .set("movieTheaters", null)
                .sampleList(5);

        movieRepository.saveAll(Stream.concat(movies1.stream(), movies2.stream()).toList());

        var predicate = ExpressionUtils.allOf(movieEntity.title.contains("탑건"));

        var result = movieRepository.findAllBy(predicate);

        assertEquals(2, result.size());
    }
}
