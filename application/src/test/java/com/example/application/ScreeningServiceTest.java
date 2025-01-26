//package com.example.application;
//
//import com.example.application.screening.service.ScreeningService;
//import com.example.domain.movie.entity.Movie;
//import com.example.domain.screening.entity.Screening;
//import com.example.domain.screening.repository.ScreeningRepository;
//import com.example.domain.theater.entity.Theater;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//
//@ExtendWith(MockitoExtension.class)
//class ScreeningServiceTest {
//
//    @Mock
//    private ScreeningRepository screeningRepository;
//
//    @InjectMocks
//    private ScreeningService screeningService;
//
//    @Test
//    @DisplayName("상영 중인 영화 조회 테스트")
//    void testGetScreengings() {
//        LocalDateTime now = LocalDateTime.of(2025, 1, 17, 15, 0,7);
//
//        Screening screening1 = Screening.builder()
//                .startTime(now.plusHours(4))
//                .endTime(now.plusHours(6))
//                .movie(Movie.builder()
//                        .title("Inception1")
//                        .rating(Movie.Rating.AGE_15)
//                        .releaseDate(LocalDateTime.of(2025, 2, 16, 0, 0,8))
//                        .thumbnail("inception1.jpg")
//                        .genre(Movie.Genre.ACTION)
//                        .runtimeMinutes(120)
//                        .build())
//                .theater(Theater.builder()
//                        .name("IMAX")
//                        .build())
//                .build();
//
//        List<Screening> mockScreening = List.of(screening1);
//
//        when(screeningRepository.findScreeningWithDetailsByStartTimeAfter(any(LocalDateTime.class)))
//                .thenReturn(mockScreening);
//
//        List<Screening> results = screeningService.getScreengings();
//
//        assertThat(results).isNotNull();
//        assertThat(results).hasSize(1);
//        assertThat(results.get(0).getMovie().getTitle()).isEqualTo("Inception1");
//
//    }
//
//
//}
