package com.movie.domain.fixture;

import com.movie.domain.entity.*;

import java.time.LocalDateTime;
import java.util.List;

public class TestFixture {
    
    public static Movie createMovie() {
        return Movie.builder()
                .title("Test Movie")
                .grade("15세 이상")
                .genre("액션")
                .description("테스트 영화입니다.")
                .runningTime(120)
                .releaseDate(LocalDateTime.now())
                .thumbnailUrl("http://example.com/thumbnail.jpg")
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
        LocalDateTime startTime = LocalDateTime.now().plusDays(1);
        return Schedule.builder()
                .movie(movie)
                .startTime(startTime)
                .endTime(startTime.plusHours(2))
                .build();
    }
    
    public static Seat createSeat() {
        return Seat.builder()
                .seatNumber("A1")
                .build();
    }
    
    public static Reservation createReservation(User user, Schedule schedule, List<Seat> seats) {
        return Reservation.builder()
                .reservationNumber("TEST-" + System.currentTimeMillis())
                .user(user)
                .schedule(schedule)
                .seats(seats)
                .build();
    }
} 