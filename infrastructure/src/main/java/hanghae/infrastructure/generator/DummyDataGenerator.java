package hanghae.infrastructure.generator;

import hanghae.domain.types.movie.AgeRating;
import hanghae.domain.types.movie.Genre;
import hanghae.domain.types.movie.ReleaseDate;
import hanghae.domain.types.movie.RunningTime;
import hanghae.domain.types.showtime.Schedule;
import hanghae.infrastructure.domain.entity.*;
import hanghae.infrastructure.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//@Component
// 상영관 50 개, 영화 500 개, 상영 시간 10_000 개
@RequiredArgsConstructor
public class DummyDataGenerator implements CommandLineRunner {

    private final JpaMovieRepository movieRepository;
    private final JpaShowtimeRepository showtimeRepository;
    private final JpaTheaterRepository theaterRepository;
    private final JpaMovieTheaterRepository movieTheaterRepository;
    private final JpaTheaterShowtimeRepository theaterShowtimeRepository;

    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        generateTheaters(50);
        generateMovies(500);
        generateShowtimes(10_000);

        linkMoviesToTheaters();
        linkShowtimesToTheaters();
    }

    private void generateTheaters(int count) {
        List<TheaterEntity> theaters = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            TheaterEntity theater = new TheaterEntity();
            theater.setTheaterName("Theater #" + i);
            theaters.add(theater);
        }
        theaterRepository.saveAll(theaters);
    }

    private void generateMovies(int count) {
        List<MovieEntity> movies = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            MovieEntity movie = new MovieEntity();
            String title = "Movie #" + i;
            movie.setTitleAndThumbnailUrl(title, "https://www.dummy-url/" + i);

//            ReleaseDate releaseDate = new ReleaseDate(
//                    2023 + random.nextInt(6), // 2023~2028 사이
//                    1 + random.nextInt(12),   // 1~12월
//                    1 + random.nextInt(28)    // 1~28일
//            );
            LocalDate releaseDate = LocalDate.of(
                    2023 + random.nextInt(6), // 2023~2028 사이
                    1 + random.nextInt(12),   // 1~12월
                    1 + random.nextInt(28)    // 1~28일
            );
            RunningTime runningTime = new RunningTime(random.nextInt(70, 181)); // 70 분 ~ 180 분
            AgeRating ageRating = randomEnum(AgeRating.class);
            Genre genre = randomEnum(Genre.class);

            movie.setValueObjects(releaseDate, runningTime, ageRating, genre);

            movies.add(movie);
        }
        movieRepository.saveAll(movies);
    }

    private void generateShowtimes(int count) {
        List<ShowtimeEntity> showtimes = new ArrayList<>();
        long totalMovies = movieRepository.count();

        for (int i = 1; i <= count; i++) {
            ShowtimeEntity showtime = new ShowtimeEntity();
            // 랜덤하게 movieId를 지정해서 setMovie(...)
            long randomMovieId = 1 + random.nextInt((int) totalMovies);

            MovieEntity movieRef = movieRepository.getReferenceById(randomMovieId);
            showtime.setMovie(movieRef);

            // start/endTime
            // endTime: 편의를 위해 runningTime 과 관계없이 startTime 의 2시간 뒤로 고정
            LocalDateTime startTime = randomDateTimeIn2025();
            LocalDateTime endTime   = startTime.plusHours(2);
            Schedule schedule = new Schedule(startTime, endTime);
            showtime.setSchedule(schedule);

            showtimes.add(showtime);

            // 메모리/성능 이슈 방지: 일정 크기마다 flush/clear 가능
            if (i % 1000 == 0) {
                showtimeRepository.saveAll(showtimes);
                showtimes.clear();
            }
        }
        // 남은 쇼타임들 저장
        if (!showtimes.isEmpty()) {
            showtimeRepository.saveAll(showtimes);
        }
    }

    private LocalDateTime randomDateTimeIn2025() {
        // 2025년 내 임의 날짜/시간
        LocalDateTime base = LocalDateTime.of(2025, 1, 1, 0, 0);
        int plusDays  = random.nextInt(365);
        int plusHours = random.nextInt(24);
        int plusMin   = random.nextInt(60);
        return base.plusDays(plusDays).plusHours(plusHours).plusMinutes(plusMin);
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> enumClass) {
        Random random = new Random();
        int index = random.nextInt(enumClass.getEnumConstants().length);

        return enumClass.getEnumConstants()[index];
    }

    private void linkMoviesToTheaters() {
        List<MovieEntity> movies   = movieRepository.findAll();
        List<TheaterEntity> theaters = theaterRepository.findAll();
        List<MovieTheaterEntity> bridgingList = new ArrayList<>();

        // 각 Movie 마다 1~5개 극장에 랜덤 매핑
        long movieTheaterPk = 1;
        for (MovieEntity movie : movies) {
            int howMany = 1 + random.nextInt(5);     // 1~5개
            // theaters 중에서 howMany 개 랜덤 선택
            List<TheaterEntity> chosen = pickRandomTheaters(theaters, howMany);

            for (TheaterEntity th : chosen) {
                MovieTheaterEntity mt = new MovieTheaterEntity();
                mt.setMovieAndTheater(movie, th);

                bridgingList.add(mt);
            }
        }

        movieTheaterRepository.saveAll(bridgingList);
    }

    private void linkShowtimesToTheaters() {
        List<ShowtimeEntity> showtimes = showtimeRepository.findAll();
        List<TheaterEntity> theaters    = theaterRepository.findAll();
        List<TheaterShowtimeEntity> bridgingList = new ArrayList<>();

        for (ShowtimeEntity showtime : showtimes) {
            // 각 쇼타임이 1~3개 극장에 매핑
            int howMany = 1 + random.nextInt(3);
            List<TheaterEntity> chosen = pickRandomTheaters(theaters, howMany);

            for (TheaterEntity th : chosen) {
                TheaterShowtimeEntity ts = new TheaterShowtimeEntity();
                ts.setTheaterAndShowtime(th, showtime);

                bridgingList.add(ts);
            }
        }
        theaterShowtimeRepository.saveAll(bridgingList);
    }

    // 헬퍼 메서드: theaters 중 몇 개를 랜덤 추출
    private List<TheaterEntity> pickRandomTheaters(List<TheaterEntity> all, int howMany) {
        // 간단 구현: shuffle 후 subList
        List<TheaterEntity> copy = new ArrayList<>(all);
         Collections.shuffle(copy, random);
         return copy.subList(0, howMany);

        // 혹은 random.sample 비슷한 로직 수동 구현
//        List<TheaterEntity> result = new ArrayList<>();
//        for (int i = 0; i < howMany; i++) {
//            TheaterEntity chosen = copy.remove(random.nextInt(copy.size()));
//            result.add(chosen);
//        }
//        return result;
    }

}