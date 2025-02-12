package hellojpa.controller;

import hellojpa.domain.Genre;
import hellojpa.domain.Movie;
import hellojpa.domain.VideoRating;
import hellojpa.dto.RateLimitResponseDto;
import hellojpa.dto.ScreeningDto;
import hellojpa.dto.SearchCondition;
import hellojpa.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ScreeningControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ScreeningController screeningController;

    @Autowired
    private MovieRepository movieRepository;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // MockMvc 설정 (Spring의 AOP, Interceptor 적용 가능)
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        // 테스트용 데이터 저장
        Movie movie1 = new Movie("Movie1", VideoRating.ALL, LocalDate.now(), "https://xxx", 120, Genre.DRAMA);
        Movie movie2 = new Movie("Movie2", VideoRating.ALL, LocalDate.now(), "https://xxx", 120, Genre.ACTION);

        movieRepository.save(movie1);
        movieRepository.save(movie2);
    }

    @Test
    void should_ReturnCurrentScreenings_When_ValidRequest() throws Exception {
        // given
        SearchCondition searchCondition = new SearchCondition("Movie1", "DRAMA");

        // when
        RateLimitResponseDto<List<ScreeningDto>> currentScreenings = screeningController.getCurrentScreenings(searchCondition);

        // then
        assertNotNull(currentScreenings);
        assertEquals(200, currentScreenings.getStatus());
        assertEquals("Movie1", currentScreenings.getData().get(0).getTitle());
    }

    @Test
    void should_ReturnTooManyRequests_When_ExceedingRateLimit() throws Exception {
        // given
        String title = "Movie1";
        String genre = "DRAMA";

        // when & then
        // 50회 정상 요청을 1초에 걸쳐 수행
        for (int i = 0; i < 50; i++) {
            mockMvc.perform(get("/screening/movies")
                            .param("title", title)
                            .param("genre", genre)
                            .contentType(MediaType.APPLICATION_JSON));

            Thread.sleep(20);
        }

        // 51번째 요청에서 429 Too Many Requests 발생 확인
        mockMvc.perform(get("/screening/movies")
                        .param("title", title)
                        .param("genre", genre)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isTooManyRequests())
                .andExpect(jsonPath("$.status").value(429))
                .andExpect(jsonPath("$.code").value("RATE_LIMIT_EXCEEDED"));
    }
}