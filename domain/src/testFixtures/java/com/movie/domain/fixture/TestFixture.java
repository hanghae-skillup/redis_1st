package com.movie.domain.fixture;

import com.movie.domain.entity.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TestFixture {

    public static Movie createMovie() {
        return Movie.builder()
                .title("Test Movie")
                .genre("Action")
                .description("Test Description")
                .duration(120)
                .releaseDate(LocalDateTime.now())
                .build();
    }

    public static User createUser() {
        return User.builder()
                .email("test@example.com")
                .password("password")
                .name("Test User")
                .build();
    }

    public static Schedule createSchedule(Movie movie) {
        return Schedule.builder()
                .movie(movie)
                .startTime(LocalDateTime.now().plusDays(1))
                .endTime(LocalDateTime.now().plusDays(1).plusHours(2))
                .build();
    }

    public static Seat createSeat() {
        return Seat.builder()
                .rowNumber("A")
                .columnNumber(1)
                .build();
    }

    public static Reservation createReservation(User user, Schedule schedule, List<Seat> seats) {
        return Reservation.builder()
                .reservationNumber(UUID.randomUUID().toString())
                .user(user)
                .schedule(schedule)
                .seats(seats)
                .build();
    }
} 