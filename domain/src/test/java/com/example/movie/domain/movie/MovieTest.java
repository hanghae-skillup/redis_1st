package com.example.movie.domain.movie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

class MovieTest {

    @Nested
    @DisplayName("영화 생성")
    class constructorTest {

        @Test
        @DisplayName("성공")
        void success_0() {
            // given
            String title = "Action Movie 1";
            MovieCategory category = MovieCategory.ACTION;
            AgeRatingType ageRating = AgeRatingType.FIFTEEN;
            Integer durationMin = 120;
            LocalDate releaseDate = LocalDate.of(2023, 1, 1);
            MovieThumbnail thumbnail = new MovieThumbnail("http://thumbnail.com", "thumbnail");

            // when
            Movie movie = new Movie(title, category, ageRating, durationMin, releaseDate, thumbnail);

            // then
            assertNotNull(movie);
            assertEquals(title, movie.title());
            assertEquals(category, movie.category());
            assertEquals(ageRating, movie.ageRating());
            assertEquals(durationMin, movie.durationMin());
            assertEquals(releaseDate, movie.releaseDate());
            assertEquals(thumbnail, movie.thumbnail());
        }

        @NullSource
        @ParameterizedTest
        @DisplayName("실패 - 제목이 null")
        void fail_0(String wrongTitle) {
            // given
            MovieCategory category = MovieCategory.ACTION;
            AgeRatingType ageRating = AgeRatingType.FIFTEEN;
            Integer durationMin = 120;
            LocalDate releaseDate = LocalDate.of(2023, 1, 1);
            MovieThumbnail thumbnail = new MovieThumbnail("http://thumbnail.com", "thumbnail");

            // when
            // then
            assertThrows(NullPointerException.class, () -> new Movie(wrongTitle, category, ageRating, durationMin, releaseDate, thumbnail));
        }

        @EmptySource
        @ParameterizedTest
        @DisplayName("실패 - 제목이 empty")
        void fail_1(String wrongTitle) {
            // given
            MovieCategory category = MovieCategory.ACTION;
            AgeRatingType ageRating = AgeRatingType.FIFTEEN;
            Integer durationMin = 120;
            LocalDate releaseDate = LocalDate.of(2023, 1, 1);
            MovieThumbnail thumbnail = new MovieThumbnail("http://thumbnail.com", "thumbnail");

            // when
            // then
            assertThrows(IllegalArgumentException.class, () -> new Movie(wrongTitle, category, ageRating, durationMin, releaseDate, thumbnail));
        }

        @Test
        @DisplayName("실패 - 제목이 최대 길이 초과")
        void fail_2() {
            // given
            String wrongTitle = "a".repeat(256);
            MovieCategory category = MovieCategory.ACTION;
            AgeRatingType ageRating = AgeRatingType.FIFTEEN;
            Integer durationMin = 120;
            LocalDate releaseDate = LocalDate.of(2023, 1, 1);
            MovieThumbnail thumbnail = new MovieThumbnail("http://thumbnail.com", "thumbnail");

            // when
            // then
            assertThrows(IllegalArgumentException.class, () -> new Movie(wrongTitle, category, ageRating, durationMin, releaseDate, thumbnail));
        }

        @NullSource
        @ParameterizedTest
        @DisplayName("실패 - 카테고리가 null")
        void fail_3(MovieCategory wrongCategory) {
            // given
            String title = "Action Movie 1";
            AgeRatingType ageRating = AgeRatingType.FIFTEEN;
            Integer durationMin = 120;
            LocalDate releaseDate = LocalDate.of(2023, 1, 1);
            MovieThumbnail thumbnail = new MovieThumbnail("http://thumbnail.com", "thumbnail");

            // when
            // then
            assertThrows(NullPointerException.class, () -> new Movie(title, wrongCategory, ageRating, durationMin, releaseDate, thumbnail));
        }

        @NullSource
        @ParameterizedTest
        @DisplayName("실패 - 연령등급이 null")
        void fail_4(AgeRatingType wrongAgeRating) {
            // given
            String title = "Action Movie 1";
            MovieCategory category = MovieCategory.ACTION;
            Integer durationMin = 120;
            LocalDate releaseDate = LocalDate.of(2023, 1, 1);
            MovieThumbnail thumbnail = new MovieThumbnail("http://thumbnail.com", "thumbnail");

            // when
            // then
            assertThrows(NullPointerException.class, () -> new Movie(title, category, wrongAgeRating, durationMin, releaseDate, thumbnail));
        }

        @NullSource
        @ParameterizedTest
        @DisplayName("실패 - 상영 시간이 null")
        void fail_5(Integer wrongDurationMin) {
            // given
            String title = "Action Movie 1";
            MovieCategory category = MovieCategory.ACTION;
            AgeRatingType ageRating = AgeRatingType.FIFTEEN;
            LocalDate releaseDate = LocalDate.of(2023, 1, 1);
            MovieThumbnail thumbnail = new MovieThumbnail("http://thumbnail.com", "thumbnail");

            // when
            // then
            assertThrows(NullPointerException.class, () -> new Movie(title, category, ageRating, wrongDurationMin, releaseDate, thumbnail));
        }

        @Test
        @DisplayName("실패 - 상영 시간이 0 미만")
        void fail_6() {
            // given
            String title = "Action Movie 1";
            MovieCategory category = MovieCategory.ACTION;
            AgeRatingType ageRating = AgeRatingType.FIFTEEN;
            Integer wrongDurationMin = -1;
            LocalDate releaseDate = LocalDate.of(2023, 1, 1);
            MovieThumbnail thumbnail = new MovieThumbnail("http://thumbnail.com", "thumbnail");

            // when
            // then
            assertThrows(IllegalArgumentException.class, () -> new Movie(title, category, ageRating, wrongDurationMin, releaseDate, thumbnail));
        }

        @NullSource
        @ParameterizedTest
        @DisplayName("실패 - 개봉일이 null")
        void fail_7(LocalDate wrongReleaseDate) {
            // given
            String title = "Action Movie 1";
            MovieCategory category = MovieCategory.ACTION;
            AgeRatingType ageRating = AgeRatingType.FIFTEEN;
            Integer durationMin = 120;
            MovieThumbnail thumbnail = new MovieThumbnail("http://thumbnail.com", "thumbnail");

            // when
            // then
            assertThrows(NullPointerException.class, () -> new Movie(title, category, ageRating, durationMin, wrongReleaseDate, thumbnail));
        }

        @NullSource
        @ParameterizedTest
        @DisplayName("실패 - 썸네일이 null")
        void fail_8(MovieThumbnail wrongThumbnail) {
            // given
            String title = "Action Movie 1";
            MovieCategory category = MovieCategory.ACTION;
            AgeRatingType ageRating = AgeRatingType.FIFTEEN;
            Integer durationMin = 120;
            LocalDate releaseDate = LocalDate.of(2023, 1, 1);

            // when
            // then
            assertThrows(NullPointerException.class, () -> new Movie(title, category, ageRating, durationMin, releaseDate, wrongThumbnail));
        }
    }
}