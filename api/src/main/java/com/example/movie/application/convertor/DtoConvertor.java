package com.example.movie.application.convertor;

import com.example.movie.repository.dto.MoviesNowShowingDetailDto;
import com.example.movie.application.dto.MoviesNowShowingDetail;
import com.example.movie.application.dto.ScreeningTimeDetail;
import com.example.movie.application.dto.ScreeningsDetail;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class DtoConvertor {
    public List<MoviesNowShowingDetail> moviesNowScreening(List<MoviesNowShowingDetailDto> dbResults) {
        return dbResults.stream()
                .collect(Collectors.groupingBy(MoviesNowShowingDetailDto::getMovieId))
                .entrySet().stream()
                .map(entry -> {
                    Long movieId = entry.getKey();
                    List<MoviesNowShowingDetailDto> groupedByMovie = entry.getValue();


                    MoviesNowShowingDetailDto firstEntry = groupedByMovie.get(0);


                    List<ScreeningsDetail> screeningsDetails = groupedByMovie.stream()
                            .collect(Collectors.groupingBy(MoviesNowShowingDetailDto::getTheaterId))
                            .entrySet().stream()
                            .map(theaterEntry -> {
                                Long theaterId = theaterEntry.getKey();
                                String theaterName = theaterEntry.getValue().get(0).getTheaterName();
                                List<ScreeningTimeDetail> screeningTimes = theaterEntry.getValue().stream()
                                        .sorted(Comparator.comparing(MoviesNowShowingDetailDto::getStartAt))
                                        .map(dto -> new ScreeningTimeDetail(dto.getStartAt(), dto.getEndAt()))
                                        .toList();
                                return new ScreeningsDetail(theaterId, theaterName, screeningTimes);
                            })
                            .toList();

                    // Create the final DTO
                    return new MoviesNowShowingDetail(
                            movieId, // Add movieId here
                            firstEntry.getMovieName(),
                            firstEntry.getGrade(),
                            firstEntry.getReleaseDate(),
                            firstEntry.getThumbnail(),
                            firstEntry.getRunningTime(),
                            firstEntry.getGenre(),
                            screeningsDetails
                    );
                })
                .toList();
    }
}
