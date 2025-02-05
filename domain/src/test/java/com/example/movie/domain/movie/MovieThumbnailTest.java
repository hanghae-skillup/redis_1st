package com.example.movie.domain.movie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

class MovieThumbnailTest {

    @Nested
    @DisplayName("영화 썸네일 생성")
    class ConstructorTest {

        static Stream<Arguments> provideInvalidUrls() {
            return Stream.of(
                arguments(""),
                arguments(" "),
                arguments("invalid-url"),
                arguments("wrongthumbnail.com")
            );
        }

        static Stream<Arguments> provideInvalidPaths() {
            return Stream.of(
                arguments(""),
                arguments(" ".repeat(256))
            );
        }

        @Test
        @DisplayName("영화 썸네일 생성 성공")
        void success_0() {
            // given
            String url = "http://thumbnail.com";
            String path = "thumbnail";

            // when
            MovieThumbnail thumbnail = new MovieThumbnail(url, path);

            // then
            assertNotNull(thumbnail);
            assertEquals(url, thumbnail.url());
            assertEquals(path, thumbnail.path());
        }

        @NullSource
        @ParameterizedTest
        @DisplayName("실패 - URL이 null")
        void fail_0(String wrongUrl) {
            // given
            String path = "thumbnail";

            // when then
            assertThrows(NullPointerException.class,
                () -> new MovieThumbnail(wrongUrl, path));
        }

        @ParameterizedTest
        @MethodSource("provideInvalidUrls")
        @DisplayName("실패 - URL이 유효하지 않음")
        void fail_1(String wrongUrl) {
            // given
            String path = "thumbnail";

            // when then
            assertThrows(IllegalArgumentException.class,
                () -> new MovieThumbnail(wrongUrl, path));
        }

        @NullSource
        @ParameterizedTest
        @DisplayName("실패 - 경로가 null")
        void fail_2(String wrongPath) {
            // given
            String url = "http://thumbnail.com";

            // when then
            assertThrows(NullPointerException.class,
                () -> new MovieThumbnail(url, wrongPath));
        }

        @ParameterizedTest
        @MethodSource("provideInvalidPaths")
        @DisplayName("실패 - 경로가 유효하지 않음")
        void fail_3(String wrongPath) {
            // given
            String url = "http://thumbnail.com";

            // when then
            assertThrows(IllegalArgumentException.class,
                () -> new MovieThumbnail(url, wrongPath));
        }
    }
}