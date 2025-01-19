package com.example.movie.domain.movie;

import static com.example.movie.domain.movie.AgeRatingType.FIFTEEN;
import static com.example.movie.domain.movie.MovieCategory.ACTION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.time.LocalDate;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

public class MovieTest {

    @Nested
    @DisplayName("영화 생성")
    class constructorTest {

        @Test
        @DisplayName("성공")
        void success_0() {
            // given
            String title = "Action Movie 1";
            MovieCategory category = ACTION;
            AgeRatingType ageRating = FIFTEEN;
            Integer durationMin = 120;
            LocalDate releaseDate = LocalDate.of(2023, 1, 1);
            MovieThumbnail thumbnail = new MovieThumbnail("http://thumbnail.com", "thumbnail");
            TheaterType theater = TheaterType.ROOM_1;

            // when
            Movie movie = new Movie(title, category, ageRating, durationMin, releaseDate, theater, thumbnail);

            // then
            assertNotNull(movie);
            assertEquals(title, movie.title());
            assertEquals(category, movie.category());
            assertEquals(ageRating, movie.ageRating());
            assertEquals(durationMin, movie.durationMin());
            assertEquals(releaseDate, movie.releaseDate());
            assertEquals(theater, movie.theater());
            assertEquals(thumbnail, movie.thumbnail());
            assertEquals(thumbnail.url(), "http://thumbnail.com");
            assertEquals(thumbnail.path(), "thumbnail");
            assertEquals(movie.thumbnail().movie(), movie);
            assertEquals(0, movie.schedules().size());
        }

        @NullSource
        @ParameterizedTest
        @DisplayName("실패 - 제목이 null")
        void fail_0(String wrongTitle) {
            // given when then
            assertThrows(NullPointerException.class,
                () -> new Movie(wrongTitle, ACTION, FIFTEEN, 120,
                    LocalDate.of(2023, 1, 1), TheaterType.ROOM_1,
                    new MovieThumbnail("http://thumbnail.com", "thumbnail")));
        }

        static Stream<Arguments> provideInvalidTitles() {
            return Stream.of(
                arguments(""),
                arguments(" "),
                arguments("a".repeat(256))
            );
        }

        @MethodSource("provideInvalidTitles")
        @ParameterizedTest
        @DisplayName("실패 - 제목이 empty / 최대 길이 초과")
        void fail_1(String wrongTitle) {
            // given when then
            assertThrows(IllegalArgumentException.class,
                () -> new Movie(wrongTitle, ACTION, FIFTEEN, 120,
                    LocalDate.of(2023, 1, 1), TheaterType.ROOM_1,
                    new MovieThumbnail("http://thumbnail.com", "thumbnail")));
        }

        @NullSource
        @ParameterizedTest
        @DisplayName("실패 - 카테고리가 null")
        void fail_2(MovieCategory wrongCategory) {
            // given when then
            assertThrows(NullPointerException.class,
                () -> new Movie("Action Movie 1", wrongCategory, FIFTEEN, 120,
                    LocalDate.of(2023, 1, 1), TheaterType.ROOM_1,
                    new MovieThumbnail("http://thumbnail.com", "thumbnail")));
        }

        @NullSource
        @ParameterizedTest
        @DisplayName("실패 - 연령등급이 null")
        void fail_3(AgeRatingType wrongAgeRating) {
            // given when then
            assertThrows(NullPointerException.class,
                () -> new Movie("Action Movie 1", ACTION, wrongAgeRating, 120,
                    LocalDate.of(2023, 1, 1), TheaterType.ROOM_1,
                    new MovieThumbnail("http://thumbnail.com", "thumbnail")));
        }

        @NullSource
        @ParameterizedTest
        @DisplayName("실패 - 상영 시간이 null")
        void fail_4(Integer wrongDurationMin) {
            // given when then
            assertThrows(NullPointerException.class,
                () -> new Movie("Action Movie 1", ACTION, FIFTEEN, wrongDurationMin,
                    LocalDate.of(2023, 1, 1), TheaterType.ROOM_1,
                    new MovieThumbnail("http://thumbnail.com", "thumbnail")));
        }

        @Test
        @DisplayName("실패 - 상영 시간이 0 미만")
        void fail_5() {
            // given
            Integer wrongDurationMin = -1;

            // when then
            assertThrows(IllegalArgumentException.class,
                () -> new Movie("Action Movie 1", ACTION, FIFTEEN, wrongDurationMin,
                    LocalDate.of(2023, 1, 1), TheaterType.ROOM_1,
                    new MovieThumbnail("http://thumbnail.com", "thumbnail")));
        }

        @NullSource
        @ParameterizedTest
        @DisplayName("실패 - 개봉일이 null")
        void fail_6(LocalDate wrongReleaseDate) {
            // given when then
            assertThrows(NullPointerException.class,
                () -> new Movie("Action Movie 1", ACTION, FIFTEEN, 120,
                    wrongReleaseDate, TheaterType.ROOM_1,
                    new MovieThumbnail("http://thumbnail.com", "thumbnail")));
        }

        @NullSource
        @ParameterizedTest
        @DisplayName("실패 - 상영관이 null")
        void fail_7(TheaterType wrongTheater) {
            // given when then
            assertThrows(NullPointerException.class,
                () -> new Movie("Action Movie 1", ACTION, FIFTEEN, 120,
                    LocalDate.of(2023, 1, 1), wrongTheater,
                    new MovieThumbnail("http://thumbnail.com", "thumbnail")));
        }

        @NullSource
        @ParameterizedTest
        @DisplayName("실패 - 썸네일이 null")
        void fail_8(MovieThumbnail wrongThumbnail) {
            // given when then
            assertThrows(NullPointerException.class,
                () -> new Movie("Action Movie 1", ACTION, FIFTEEN, 120,
                    LocalDate.of(2023, 1, 1), TheaterType.ROOM_1,
                    null));
        }
    }

    public static Movie makeMovie(){
        return new Movie("Action Movie 1", ACTION, FIFTEEN, 120,
            LocalDate.of(2023, 1, 1), TheaterType.ROOM_1,
            new MovieThumbnail("http://thumbnail.com", "thumbnail"));
    }
}