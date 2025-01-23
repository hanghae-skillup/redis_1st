package project.redis.presentation.screening.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import project.redis.application.screening.port.inbound.ScreeningQueryUseCase;
import project.redis.application.screening.port.inbound.ScreeningsQueryParam;
import project.redis.domain.cinema.Cinema;
import project.redis.domain.genre.Genre;
import project.redis.domain.movie.Movie;
import project.redis.domain.movie.RatingClassification;
import project.redis.domain.screening.Screening;

@ExtendWith(MockitoExtension.class)
class ScreeningControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ScreeningQueryUseCase screeningQueryUseCase;

    @InjectMocks
    private ScreeningController screeningController;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(screeningController)
                .build();
    }


    @DisplayName("상영 시간표 가져오기 - 성공")
    @Test
    void testGetScreenings() throws Exception {
        Cinema cinema = Cinema.generateCinema(UUID.randomUUID(), "cinema");

        UUID genreId = UUID.randomUUID();
        Genre genre = Genre.generateGenre(genreId, "액션");

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = now.plusMinutes(120);
        UUID movieIdA = UUID.randomUUID();
        UUID movieIdB = UUID.randomUUID();
        LocalDate releaseDateNow = LocalDate.now();

        Movie movieA = Movie.generateMovie(
                movieIdA, "movieA", RatingClassification.ALL,
                releaseDateNow, "thum", 120, genre);

        Movie movieB = Movie.generateMovie(
                movieIdB, "movieB", RatingClassification.ALL,
                releaseDateNow.plusDays(1), "thum", 120, genre);


        Screening screening1 = Screening.generateScreening(
                UUID.randomUUID(), now, now.plusMinutes(120), movieA, cinema);

        Screening screening2 = Screening.generateScreening(
                UUID.randomUUID(), now.plusMinutes(120), now.plusMinutes(240), movieA, cinema);

        Screening screening3 = Screening.generateScreening(
                UUID.randomUUID(), now.plusMinutes(240), now.plusMinutes(360), movieB, cinema);

        Screening screening4 = Screening.generateScreening(
                UUID.randomUUID(), now.plusMinutes(360), now.plusMinutes(480), movieB, cinema);

        ArrayList<Screening> screenings = new ArrayList<>();
        screenings.add(screening1);
        screenings.add(screening2);
        screenings.add(screening3);
        screenings.add(screening4);

        when(screeningQueryUseCase.getScreenings(ScreeningsQueryParam.builder()
                        .maxScreeningDay(2)
                .build())).thenReturn(screenings);

        ResultActions resultActions = mockMvc.perform(
                get("/api/v1/screenings")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        resultActions
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$[0].movieTitle")
                                .value(movieB.getTitle())
                )
                .andExpect(
                        jsonPath("$[0].screenings[0].screeningId")
                                .value(screening3.getScreeningId().toString())
                );
    }


}