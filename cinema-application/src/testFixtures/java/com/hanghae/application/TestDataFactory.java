package com.hanghae.application;

import com.hanghae.application.dto.request.MovieReservationRequestDto;
import com.hanghae.domain.model.*;
import com.hanghae.domain.model.enums.MovieGenre;
import com.hanghae.domain.model.enums.MovieRating;
import com.hanghae.domain.model.enums.ScreenSeat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * java-test-fixtures - 테스트를 수행하기 위한 일관적이고 고정된 상태를 설정
 * 테스트시 사용하는 데이터들(영화정보, 회원정보 등)에 대한 Fixture의 재사용성을 높이기 위해 따로 분리
 */
public class TestDataFactory {

    // 영화 예매 요청 DTO 생성
    public static MovieReservationRequestDto createMovieReservationRequestDto() {
        return new MovieReservationRequestDto(1L, 1L, ScreenSeat.A01, 2);
    }

    public static MovieReservationRequestDto createMovieReservationRequestDto(Long scheduleId, Long memberId, int seatCount, ScreenSeat seat) {
        return new MovieReservationRequestDto(scheduleId, memberId, seat, seatCount);
    }

    // 첨부파일 생성
    public static UploadFile createUploadFile() {
        return new UploadFile(
            1L,
            "/file/test",
            "test.png",
            "test.png",
            99L,
            null,
            null,
            null
        );
    }

    // 영화 생성
    public static Movie createMovie() {
        return new Movie(
            1L,
            "테스트 영화",
            MovieRating.ALL,
            LocalDate.of(2024, 1, 1),
            90L,
            MovieGenre.ACTION,
            createUploadFile(),
            99L,
            null,
            null,
            null
        );
    }

    // 상영관 생성
    public static Screen createScreen() {
        return new Screen(
            1L,
            "1관",
            99L,
            null,
            null,
            null
        );
    }

    public static Screen createScreen(Long screenId, String screenName) {
        return new Screen(
                screenId,
                screenName,
                99L,
                null,
                null,
                null
        );
    }

    // 영화 상영 일정 생성
    public static ScreeningSchedule createScreeningSchedule() {
        return new ScreeningSchedule(
            1L,
            createMovie(),
            createScreen(),
            LocalDateTime.of(2024, 10, 15, 11, 11, 11),
            99L,
            null,
            null,
            null
        );
    }

    // 상영 시간표 생성
    public static ScreeningSchedule createScreeningSchedule(Long scheduleId, Screen screen) {
        return new ScreeningSchedule(
            scheduleId,
            createMovie(),
            screen,
            LocalDateTime.of(2024, 10, 15, 11, 11, 11),
            99L,
            null,
            null,
            null
        );
    }

    // 회원 생성
    public static Member createMember() {
        return new Member(
            99L,
            LocalDate.of(1995, 1, 1),
            99L,
            null,
            null,
            null
        );
    }

    public static Member createMember(Long memberId, LocalDate birthDate) {
        return new Member(
            memberId,
            birthDate,
            memberId,
            null,
            null,
            null
        );
    }

    // 좌석 배치 정보 생성
    public static ScreenSeatLayout createScreenSeatLayout() {
        return new ScreenSeatLayout(
            1L,
            createScreen(),
            "A",
            5L,
            99L,
            null,
            null,
            null
        );
    }

    public static ScreenSeatLayout createScreenSeatLayout(String seatRow, Long maxSeatNumber) {
        return new ScreenSeatLayout(
            1L,
            createScreen(),
            seatRow,
            maxSeatNumber,
            99L,
            null,
            null,
            null
        );
    }

    // 예매 정보 생성
    public static TicketReservation createTicketReservation() {
        return new TicketReservation(
            1L,
            ScreenSeat.A01,
            createScreeningSchedule(),
            createMember(),
            99L,
            null,
            null,
            null
        );
    }

    public static TicketReservation createTicketReservation(Long ticketId, ScreeningSchedule schedule, Member member, ScreenSeat seat) {
        return new TicketReservation(
            ticketId,
            seat,
            schedule,
            member,
            99L,
            null,
            null,
            null
        );
    }

    public static List<TicketReservation> createTicketReservations() {
        ScreeningSchedule screeningSchedule = createScreeningSchedule();
        Member member = createMember();

        return List.of(
                createTicketReservation(1L, screeningSchedule, member, ScreenSeat.A01),
                createTicketReservation(2L, screeningSchedule, member, ScreenSeat.A02)
        );
    }
}
