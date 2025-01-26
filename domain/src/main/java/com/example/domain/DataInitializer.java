//package com.example.domain;
//
//
//import com.example.domain.movie.entity.Movie;
//import com.example.domain.screening.entity.Screening;
//import com.example.domain.seat.entity.Seat;
//import com.example.domain.seat.repository.SeatRepository;
//import com.example.domain.theater.entity.Theater;
//import com.example.domain.movie.repository.MovieRepository;
//import com.example.domain.screening.repository.ScreeningRepository;
//import com.example.domain.theater.repository.TheaterRepository;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//@Component
//@RequiredArgsConstructor
//public class DataInitializer {
//    private final MovieRepository movieRepository;
//    private final TheaterRepository theaterRepository;
//    private final ScreeningRepository screeningRepository;
//    private final SeatRepository seatRepository;
//
//    private final Random random = new Random();
//
//     @PostConstruct
//    public void init() {
//        // 1. 영화 데이터 생성
//        List<Movie> movies = createMovies();
//        movieRepository.saveAll(movies);
//
//        // 2. 상영관 데이터 생성
//        List<Theater> theaters = createTheaters();
//        theaterRepository.saveAll(theaters);
//
//        // 3. 상영 정보 데이터 생성
//        List<Screening> screenings = createScreenings(movies, theaters);
//        screeningRepository.saveAll(screenings);
//
//        // 4. 좌석 데이터 생성
//        List<Seat> seats = createSeats(theaters);
//        seatRepository.saveAll(seats);
//
//
//        System.out.println("Initial data created!");
//    }
//
//
//
//
//    private List<Movie> createMovies() {
//        List<Movie> movies = new ArrayList<>();
//        for (int i = 1; i <= 500; i++) {
//            movies.add(Movie.builder()
//                    .title("Movie " + i)
//                    .rating(Movie.Rating.values()[random.nextInt(Movie.Rating.values().length)])
//                    .releaseDate(LocalDateTime.now().plusDays(random.nextInt(101) - 50)) // 현재 ±50일
//                    .thumbnail("https://example.com/thumbnails/movie" + i + ".jpg")
//                    .genre(Movie.Genre.values()[random.nextInt(Movie.Genre.values().length)])
//                    .runtimeMinutes(90 + random.nextInt(31)) // 90 ~ 120분
//                    .build());
//        }
//        return movies;
//    }
//
//    private List<Theater> createTheaters() {
//        List<Theater> theaters = new ArrayList<>();
//        for (int i = 1; i <= 10; i++) {
//            theaters.add(Theater.builder()
//                    .name("Theater " + i)
//                    .build());
//        }
//        return theaters;
//    }
//
//    private List<Screening> createScreenings(List<Movie> movies, List<Theater> theaters) {
//        List<Screening> screenings = new ArrayList<>();
//
//        // 현재 시간 기준
//        LocalDateTime now = LocalDateTime.now();
//
//        // 데이터 비율 설정
//        int totalScreenings = 5000;
//        int pastScreenings = (int) (totalScreenings * 0.1);  // 10% 종료된 상영
//        int upcomingScreenings = totalScreenings - pastScreenings; // 90% 예매 가능
//
//        // 1. 상영 종료된 영화 (10%)
//        for (int i = 0; i < pastScreenings; i++) {
//            Movie movie = movies.get(random.nextInt(movies.size()));
//            Theater theater = theaters.get(random.nextInt(theaters.size()));
//            LocalDateTime startTime = now.minusDays(random.nextInt(30) + 1); // 1~30일 전
//            LocalDateTime endTime = startTime.plusMinutes(movie.getRuntimeMinutes());
//            screenings.add(Screening.builder()
//                    .movie(movie)
//                    .theater(theater)
//                    .startTime(startTime)
//                    .endTime(endTime)
//                    .build());
//        }
//
//        // 2. 예매 가능한 영화 (90%)
//        for (int i = 0; i < upcomingScreenings; i++) {
//            Movie movie = movies.get(random.nextInt(movies.size()));
//            Theater theater = theaters.get(random.nextInt(theaters.size()));
//            LocalDateTime startTime = now.plusHours(random.nextInt(720)); // 0~30일 후
//            LocalDateTime endTime = startTime.plusMinutes(movie.getRuntimeMinutes());
//            screenings.add(Screening.builder()
//                    .movie(movie)
//                    .theater(theater)
//                    .startTime(startTime)
//                    .endTime(endTime)
//                    .build());
//        }
//
//        return screenings;
//    }
//
//    private List<Seat> createSeats(List<Theater> theaters) {
//        List<Seat> seatList = new ArrayList<>();
//
//        for (Theater theater : theaters) {
//            // 행: 'A' ~ 'E'
//            for (char row = 'A'; row <= 'E'; row++) {
//                // 열: 1 ~ 5
//                for (int col = 1; col <= 5; col++) {
//                    seatList.add(Seat.builder()
//                            .seatRow(String.valueOf(row)) // 행 (e.g., 'A', 'B', ...)
//                            .seatCol(String.valueOf(col)) // 열 (e.g., 1, 2, ...)
//                            .status(Seat.Status.AVAILABLE) // 기본 상태 설정
//                            .theater(theater) // 상영관 연결
//                            .build());
//                }
//            }
//        }
//
//        return seatList;
//    }
//}
