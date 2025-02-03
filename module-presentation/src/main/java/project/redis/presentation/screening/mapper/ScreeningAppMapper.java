package project.redis.presentation.screening.mapper;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import project.redis.domain.movie.Movie;
import project.redis.domain.screening.Screening;
import project.redis.presentation.screening.dto.response.GroupedScreeningResponse;
import project.redis.presentation.screening.dto.response.GroupedScreeningResponse.ScreeningDetail;

public class ScreeningAppMapper {

    public static List<GroupedScreeningResponse> toGroupedScreeningResponse(List<Screening> screenings) {
        Map<UUID, List<Screening>> groupedByMovie = screenings.stream()
                .collect(Collectors.groupingBy(screening -> screening.getMovie().getMovieId()));

        return groupedByMovie.entrySet().stream()
                .sorted(Comparator.comparing(entry ->
                        entry.getValue().get(0).getMovie().getReleaseDate(),
                        Comparator.reverseOrder())
                )
                .map(entry -> {
                    Movie movie = entry.getValue().get(0).getMovie();
                    List<ScreeningDetail> screeningDetails = entry.getValue().stream()
                            .map(screening ->
                                    ScreeningDetail.builder()
                                            .screeningId(screening.getScreeningId().toString())
                                            .screeningStartTime(screening.getScreenStartTime())
                                            .screeningEndTime(screening.getScreenEndTime())
                                            .cinemaId(screening.getCinema().getCinemaId().toString())
                                            .cinemaName(screening.getCinema().getCinemaName())
                                            .build()
                            )
                            .toList();

                    return GroupedScreeningResponse.builder()
                            .movieId(movie.getMovieId().toString())
                            .movieTitle(movie.getTitle())
                            .rating(movie.getRating().toString())
                            .releaseDate(movie.getReleaseDate())
                            .thumbnailUrl(movie.getThumbnailUrl())
                            .runningMinTime(movie.getRunningMinTime())
                            .genreId(movie.getGenre().getGenreId().toString())
                            .genreName(movie.getGenre().getGenreName())
                            .screenings(screeningDetails)
                            .build();
                })
                .toList();
    }
}
