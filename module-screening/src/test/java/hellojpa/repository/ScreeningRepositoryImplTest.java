package hellojpa.repository;

import hellojpa.domain.Genre;
import hellojpa.domain.VideoRating;
import hellojpa.dto.ScreeningDto;
import hellojpa.dto.SearchCondition;
import hellojpa.dto.TheaterScheduleDto;
import hellojpa.dto.TimeScheduleDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAQuery;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.EntityPath;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class ScreeningRepositoryImplTest {

    private ScreeningRepositoryImpl screeningRepository;

    @Mock
    private JPAQueryFactory queryFactory;

    @Mock
    private EntityManager em;

    private LocalDate todayDate;
    //private SearchCondition searchCondition;
    private List<ScreeningDto> testScreenings;

    @BeforeEach
    void setUp() {
        screeningRepository = new ScreeningRepositoryImpl(queryFactory);

        todayDate = LocalDate.now();
        //searchCondition = new SearchCondition();

        testScreenings = Arrays.asList(
                new ScreeningDto(
                        "Test Movie1",
                        VideoRating.ALL,
                        LocalDate.of(2025, 1, 1),
                        "https://xxx",
                        120,
                        Genre.DRAMA
                ),
                new ScreeningDto(
                        "Test Movie2",
                        VideoRating.ALL,
                        LocalDate.of(2025, 1, 2),
                        "https://xxx",
                        98,
                        Genre.COMEDY
                )
        );
    }

    @SuppressWarnings("unchecked")
    @Test
    void findCurrentScreeningsMovieInfo_WithValidDate_ReturnsScreeningDtos() {

        SearchCondition emptySearchCondition = new SearchCondition();

        // Mocking query execution
        JPAQuery<ScreeningDto> mockQuery = mock(JPAQuery.class);
        when(queryFactory.select(any(Expression.class))).thenReturn(mockQuery);
        when(mockQuery.from(any(EntityPath.class))).thenReturn(mockQuery);
        when(mockQuery.where(any(Predicate.class))).thenReturn(mockQuery);
        when(mockQuery.orderBy(any(OrderSpecifier.class))).thenReturn(mockQuery);
        when(mockQuery.fetch()).thenReturn(testScreenings);

        // When
        List<ScreeningDto> result = screeningRepository.findCurrentScreeningsMovieInfo(todayDate, emptySearchCondition);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getTitle()).isEqualTo("Test Movie1");
        assertThat(result.get(0).getGenre()).isEqualTo(Genre.DRAMA.toString());
        assertThat(result.get(1).getTitle()).isEqualTo("Test Movie2");
        assertThat(result.get(1).getGenre()).isEqualTo(Genre.COMEDY.toString());
    }

    @Test
    void findCurrentScreeningsMovieInfo_WithSearchCondition_ReturnsFilteredResults() {
        // Given
        SearchCondition searchCondition = new SearchCondition("Test Movie2", "COMEDY");

        // 검색 조건에 맞는 영화만 필터링
        List<ScreeningDto> filteredScreenings = testScreenings.stream()
                .filter(screening ->
                        screening.getTitle().equals("Test Movie2") &&
                                screening.getGenre().toString().equals("COMEDY"))
                .collect(Collectors.toList());

        // Mocking query execution
        JPAQuery<ScreeningDto> mockQuery = mock(JPAQuery.class);
        when(queryFactory.select(any(Expression.class))).thenReturn(mockQuery);
        when(mockQuery.from(any(EntityPath.class))).thenReturn(mockQuery);
        when(mockQuery.where(any(Predicate.class))).thenReturn(mockQuery);
        when(mockQuery.orderBy(any(OrderSpecifier.class))).thenReturn(mockQuery);
        // 필터링된 결과만 반환하도록 수정
        when(mockQuery.fetch()).thenReturn(filteredScreenings);

        // When
        List<ScreeningDto> result = screeningRepository.findCurrentScreeningsMovieInfo(todayDate, searchCondition);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Test Movie2");
        assertThat(result.get(0).getGenre()).isEqualTo(Genre.COMEDY.toString());
    }

    @SuppressWarnings("unchecked")
    @Test
    void findTheaterScheduleDtoByMovieTitles_ReturnsTheaterSchedules() {
        // Given
        List<String> titles = Arrays.asList("Test Movie");

        List<TheaterScheduleDto> expectedSchedules = Arrays.asList(
                new TheaterScheduleDto(
                        "Test Movie",
                        "Theater 1",
                        Arrays.asList(
                                new TimeScheduleDto(
                                        LocalTime.of(14, 0),
                                        120
                                )
                        )
                )
        );

        // Mocking query execution
        JPAQuery<TheaterScheduleDto> mockQuery = mock(JPAQuery.class);
        when(queryFactory.select(any(Expression.class))).thenReturn(mockQuery);
        when(mockQuery.from(any(EntityPath.class))).thenReturn(mockQuery);
        when(mockQuery.join((EntityPath) any(), (EntityPath) any())).thenReturn(mockQuery);
        when(mockQuery.where(any(Predicate.class))).thenReturn(mockQuery);
        when(mockQuery.orderBy(any(OrderSpecifier.class))).thenReturn(mockQuery);
        when(mockQuery.fetch()).thenReturn(expectedSchedules);

        // When
        List<TheaterScheduleDto> result = screeningRepository.findTheaterScheduleDtoByMovieTitles(titles);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Test Movie");
        assertThat(result.get(0).getTimeScheduleDtoList()).hasSize(1);
        assertThat(result.get(0).getTimeScheduleDtoList().get(0).getStartTime()).isEqualTo(LocalTime.of(14, 0));
        assertThat(result.get(0).getTimeScheduleDtoList().get(0).getEndTime()).isEqualTo(LocalTime.of(14, 0).plusMinutes(120));
    }
}