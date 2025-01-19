package com.example.movie.application.convertor;

import com.example.movie.repository.dto.MoviesDetailDto;
import com.example.movie.application.dto.MoviesDetail;
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
    public List<MoviesDetail> moviesNowScreening(List<MoviesDetailDto> dbResults) {
        return dbResults.stream()
                .collect(Collectors.groupingBy(MoviesDetailDto::getMovieId))
                .entrySet().stream()
                .map(entry -> {
                    Long movieId = entry.getKey();
                    List<MoviesDetailDto> groupedByMovie = entry.getValue();


                    MoviesDetailDto firstEntry = groupedByMovie.get(0);


                    List<ScreeningsDetail> screeningsDetails = groupedByMovie.stream()
                            .collect(Collectors.groupingBy(MoviesDetailDto::getTheaterId))
                            .entrySet().stream()
                            .map(theaterEntry -> {
                                Long theaterId = theaterEntry.getKey();
                                String theaterName = theaterEntry.getValue().get(0).getTheaterName();
                                List<ScreeningTimeDetail> screeningTimes = theaterEntry.getValue().stream()
                                        .sorted(Comparator.comparing(MoviesDetailDto::getStartAt))
                                        .map(dto -> new ScreeningTimeDetail(dto.getStartAt(), dto.getEndAt()))
                                        .toList();
                                return new ScreeningsDetail(theaterId, theaterName, screeningTimes);
                            })
                            .toList();

                    // Create the final DTO
                    return new MoviesDetail(
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
