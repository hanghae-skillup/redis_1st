package hellojpa.service;

import hellojpa.domain.Genre;
import hellojpa.domain.Movie;
import hellojpa.domain.Screening;
import hellojpa.domain.VideoRating;
import hellojpa.dto.ScreeningDto;
import hellojpa.dto.SearchCondition;
import hellojpa.dto.TheaterScheduleDto;
import hellojpa.dto.TimeScheduleDto;
import hellojpa.repository.ScreeningRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


public class ScreeningServiceTest {

    @Mock
    private ScreeningRepository screeningRepository;

    @InjectMocks
    private ScreeningService screeningService;

    private LocalDate todayDate;
    private SearchCondition searchCondition;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        todayDate = LocalDate.now();
        searchCondition = new SearchCondition("Movie1", "DRAMA"); // 적절한 검색 조건 설정
    }

    @Test
    void testFindCurrentScreenings() {
        // Given
        ScreeningDto mockScreeningDto = new ScreeningDto("Movie1", VideoRating.ALL, LocalDate.now(), "https://xxx", 120, Genre.DRAMA);

        TheaterScheduleDto mockTheaterScheduleDto = new TheaterScheduleDto(
                "Movie1", "Theater1", Collections.singletonList(new TimeScheduleDto(LocalTime.now(), LocalTime.now().plusMinutes(120)))
        );

        List<ScreeningDto> mockScreeningDtos = Collections.singletonList(mockScreeningDto);
        List<TheaterScheduleDto> mockTheaterScheduleDtos = Collections.singletonList(mockTheaterScheduleDto);

        // When
        when(screeningRepository.findCurrentScreeningsMovieInfo(todayDate, searchCondition)).thenReturn(mockScreeningDtos);
        when(screeningRepository.findTheaterScheduleDtoByMovieTitles(Collections.singletonList("Movie1")))
                .thenReturn(mockTheaterScheduleDtos);

        List<ScreeningDto> result = screeningService.findCurrentScreenings(todayDate, searchCondition);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Movie1", result.get(0).getTitle());
        assertEquals(1, result.get(0).getTheaterSheduleDtoList().size());
        assertEquals("Theater1", result.get(0).getTheaterSheduleDtoList().get(0).getName());

        // Verify the interaction with the repository
        verify(screeningRepository, times(1)).findCurrentScreeningsMovieInfo(todayDate, searchCondition);
        verify(screeningRepository, times(1)).findTheaterScheduleDtoByMovieTitles(Collections.singletonList("Movie1"));
    }

    @Test
    void testFindCurrentScreeningsWhenNoDataFound() {
        // Given
        List<ScreeningDto> mockScreeningDtos = Collections.emptyList();

        // When
        when(screeningRepository.findCurrentScreeningsMovieInfo(todayDate, searchCondition)).thenReturn(mockScreeningDtos);

        List<ScreeningDto> result = screeningService.findCurrentScreenings(todayDate, searchCondition);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(screeningRepository, times(1)).findCurrentScreeningsMovieInfo(todayDate, searchCondition);
    }

    @Test
    void testFindCurrentScreeningsWithException() {
        // Given
        ScreeningDto mockScreeningDto = new ScreeningDto("Movie1", VideoRating.ALL, LocalDate.now(), "https://xxx", 120, Genre.DRAMA);

        List<ScreeningDto> mockScreeningDtos = Collections.singletonList(mockScreeningDto);

        // When
        when(screeningRepository.findCurrentScreeningsMovieInfo(todayDate, searchCondition))
                .thenThrow(new RuntimeException("Database error"));

        // Then
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                screeningService.findCurrentScreenings(todayDate, searchCondition)
        );
        assertEquals("Database error", exception.getMessage());

        verify(screeningRepository, times(1)).findCurrentScreeningsMovieInfo(todayDate, searchCondition);
    }

    @Test
    void testFindCurrentScreeningsWithInvalidData() {
        // Given: Invalid data where no theater schedules are found for two movies
        ScreeningDto mockScreeningDto1 = new ScreeningDto("Movie1", VideoRating.ALL, LocalDate.now(), "https://xxx", 120, Genre.DRAMA);
        ScreeningDto mockScreeningDto2 = new ScreeningDto("Movie2", VideoRating.ALL, LocalDate.now(), "https://yyy", 90, Genre.COMEDY);

        List<ScreeningDto> mockScreeningDtos = Arrays.asList(mockScreeningDto1, mockScreeningDto2);

        // When
        when(screeningRepository.findCurrentScreeningsMovieInfo(todayDate, searchCondition)).thenReturn(mockScreeningDtos);
        when(screeningRepository.findTheaterScheduleDtoByMovieTitles(Arrays.asList("Movie1", "Movie2")))
                .thenReturn(Collections.emptyList()); // No theater schedules for both movies

        List<ScreeningDto> result = screeningService.findCurrentScreenings(todayDate, searchCondition);

        // Then
        assertNotNull(result); // Ensure that the result is not null
        assertTrue(result.size() == 2); // Ensure that there are two ScreeningDto objects
        assertTrue(result.stream().allMatch(screeningDto -> screeningDto.getTheaterSheduleDtoList().isEmpty())); // Ensure theater schedules are empty

        verify(screeningRepository, times(1)).findCurrentScreeningsMovieInfo(todayDate, searchCondition);
        verify(screeningRepository, times(1)).findTheaterScheduleDtoByMovieTitles(Arrays.asList("Movie1", "Movie2"));
    }

}
