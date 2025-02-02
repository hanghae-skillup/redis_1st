package com.example.app.movie.service;

import com.example.app.config.EmbeddedRedisConfig;
import com.example.app.movie.domain.Movie;
import com.example.app.movie.domain.Showtime;
import com.example.app.movie.domain.Theater;
import com.example.app.movie.dto.SearchMovieCommand;
import com.example.app.movie.out.persistence.adapter.MoviePersistenceAdapter;
import com.example.app.movie.type.MovieGenre;
import com.example.app.movie.type.MovieRating;
import com.example.app.movie.type.MovieStatus;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;

import static com.navercorp.fixturemonkey.api.instantiator.Instantiator.constructor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = EmbeddedRedisConfig.class)
@TestPropertySource(properties = "spring.config.location = classpath:application-test.yml")
public class SearchMovieServiceTest {

    private final int TOTAL_MOVIES = 10;

    @Mock
    private MoviePersistenceAdapter moviePersistenceAdapter;

    @InjectMocks
    private SearchMovieService searchMovieService;

    @Test
    public void 영화_검색() {
        var searchCommand = FixtureMonkey.create()
                .giveMeBuilder(SearchMovieCommand.class)
                .instantiate(constructor()
                        .parameter(String.class)
                        .parameter(MovieGenre.class))
                .sample();

        var movies = FixtureMonkey.create()
                .giveMeBuilder(Movie.class)
                .instantiate(constructor()
                        .parameter(long.class)
                        .parameter(String.class)
                        .parameter(String.class)
                        .parameter(MovieStatus.class)
                        .parameter(MovieRating.class)
                        .parameter(MovieGenre.class)
                        .parameter(String.class)
                        .parameter(int.class)
                        .parameter(LocalDate.class)
                        .parameter(new TypeReference<List<Showtime>>(){})
                        .parameter(new TypeReference<List<Theater>>(){}))
                .sampleList(TOTAL_MOVIES);

        when(moviePersistenceAdapter.loadAllMovies(any(SearchMovieCommand.class))).thenReturn(movies);

        var result = searchMovieService.searchMovies(searchCommand);

        assertEquals(TOTAL_MOVIES, result.size());
    }
}
