package com.example.movie.convertor;

import com.example.movie.dto.MoviesNowShowingDbDto;
import com.example.movie.response.MoviesNowShowingDetail;
import com.example.movie.response.ScreeningsDetail;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class DtoConvertor {
    public List<MoviesNowShowingDetail> moviesNowScreening(List<MoviesNowShowingDbDto> dbResults){
        return dbResults.stream()
                .collect(Collectors.groupingBy(MoviesNowShowingDbDto::getMovieName))
                .entrySet().stream()
                .map(entry -> {
                    String movieName = entry.getKey();
                    List<MoviesNowShowingDbDto> groupedByMovie = entry.getValue();

                    // 공통 정보 가져오기
                    MoviesNowShowingDbDto firstEntry = groupedByMovie.get(0);

                    // Theater 별 상영 시간 묶기
                    List<ScreeningsDetail> screeningsDetails = groupedByMovie.stream()
                            .collect(Collectors.groupingBy(MoviesNowShowingDbDto::getTheaterName))
                            .entrySet().stream()
                            .map(theaterEntry -> {
                                String theaterName = theaterEntry.getKey();
                                List<String> screeningTimes = theaterEntry.getValue().stream()
                                        .sorted(Comparator.comparing(MoviesNowShowingDbDto::getStartTime))
                                        .map(dto -> dto.getStartTime() + " ~ " + dto.getEndTime())
                                        .toList();
                                return new ScreeningsDetail(theaterName, screeningTimes);
                            })
                            .toList();

                    // 최종 DTO 생성
                    return new MoviesNowShowingDetail(
                            movieName,
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
