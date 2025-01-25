package com.example.entity.reservation;

import com.example.entity.movie.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReservationTest {

    @Test
    @DisplayName("하나의 상영시간에 5좌석 이상 예매시 예외가 던져진다.")
    void reserve_seat_max_five_exception() {
        Movie movie = createMovie();
        Theater theater = createTheater();
        Screening screening = createScreening(movie, theater);
        Reservation reservation = createReservation(screening);

        Seat seat1 = createSeat("A1", theater);
        Seat seat2 = createSeat("A2", theater);
        Seat seat3 = createSeat("A3", theater);
        Seat seat4 = createSeat("A4", theater);
        Seat seat5 = createSeat("A5", theater);
        Seat seat6 = createSeat("B1", theater);

        Seats seats = new Seats(List.of(seat1, seat2, seat3, seat4, seat5, seat6));

        assertThatThrownBy(() -> {
            reservation.reservation(seats);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("5개 이상의 좌성은 예약할 수 없습니다.");
    }

    @Test
    @DisplayName("연속적인 좌석이 아니면 예약할 수 없다.")
    void reserve_seat_continuous_exception() {
        Movie movie = createMovie();
        Theater theater = createTheater();
        Screening screening = createScreening(movie, theater);
        Reservation reservation = createReservation(screening);

        Seat seat1 = createSeat("A1", theater);
        Seat seat2 = createSeat("B2", theater);
        Seat seat3 = createSeat("C3", theater);
        Seat seat4 = createSeat("D4", theater);
        Seat seat5 = createSeat("E5", theater);


        Seat seat6 = createSeat("A1", theater);
        Seat seat7 = createSeat("A3", theater);
        Seat seat8 = createSeat("A5", theater);

        Seats seats1 = new Seats(List.of(seat1, seat2, seat3, seat4, seat5));
        Seats seats2 = new Seats(List.of(seat6, seat7, seat8));


        assertThatThrownBy(() -> {
            reservation.reservation(seats1);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("좌석 예매는 연속적인 좌석만 예매 가능합니다.");
        assertThatThrownBy(() -> {
            reservation.reservation(seats2);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("좌석 예매는 연속적인 좌석만 예매 가능합니다.");
    }

    private Movie createMovie() {
        return Movie.builder()
                .title("히트맨 2")
                .thumbnailUrl("https://img.youtube.com/vi/AQI/0.jpg")
                .runningTime(120)
                .releaseDate(LocalDate.of(2025, 1, 22))
                .genre(Genre.ACTION)
                .rating(Rating.ALL)
                .build();
    }

    private Theater createTheater() {
        return Theater.builder()
                .name("1관")
                .build();
    }

    private Screening createScreening(Movie movie, Theater theater) {
        return Screening.builder()
                .screeningAt(LocalDate.of(2024, 1, 24))
                .startedAt(LocalDateTime.of(2024, 1, 24, 16, 5))
                .endedAt(LocalDateTime.of(2024, 1, 24, 18, 5))
                .movie(movie)
                .theater(theater)
                .build();
    }

    private Reservation createReservation(Screening screening) {
        return Reservation.builder()
                .screening(screening)
                .build();
    }

    private Seat createSeat(String seatNumber, Theater theater) {
        return Seat.builder()
                .theater(theater)
                .seatNumber(seatNumber)
                .build();
    }

}