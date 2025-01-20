package hanghae.application.dto;

import hanghae.domain.domain.Movie;
import hanghae.domain.domain.Showtime;
import hanghae.domain.domain.Theater;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// TheaterResponse 는 좌석 정보가 필요할 때 사용할 예정
public record MovieResponse(
        String title,
        String ageRating,
        LocalDate releaseDate,
        String thumbnailUrl,
        int runningTime,
        String genre,
        List<ShowtimeResponse> showtime
        // List<TheaterResponse> theater
) {

    public static MovieResponse from(Movie movie) {
        return new MovieResponse(
                movie.getTitle(),
                movie.getAgeRating().toString(),
                movie.getReleaseDate().getDate(),
                movie.getThumbnailUrl(),
                movie.getRunningTimeAsMinutes(),
                movie.getGenre().toString(),
                showtimeResponseListFrom(movie.getShowtime())
                // theaterResponseListFrom(movie.getTheater())
        );
    }

    private static List<ShowtimeResponse> showtimeResponseListFrom(List<Showtime> showtimeList) {
        return showtimeList.stream()
                .map(ShowtimeResponse::from)
                .toList();
    }

    private static List<TheaterResponse> theaterResponseListFrom(List<Theater> theaterList) {
        return theaterList.stream()
                .map(TheaterResponse::from)
                .toList();
    }
}
