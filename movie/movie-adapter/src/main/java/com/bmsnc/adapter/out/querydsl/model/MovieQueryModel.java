package com.bmsnc.adapter.out.querydsl.model;

import com.bmsnc.applicaion.domain.model.MovieModel;
import com.bmsnc.common.dto.MovieGenre;
import com.bmsnc.common.dto.MovieGrade;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
public class MovieQueryModel {

    private Long movieId;
    private String movieName;
    private MovieGrade movieGrade;
    private LocalDateTime movieReleaseAt;
    private String movieImageUrl;
    private Long runningTimeMinutes;
    private MovieGenre movieGenre;
    private String theaterName;
    private LocalDateTime movieStartAt;

    @QueryProjection
    public MovieQueryModel(Long movieId, String movieName, MovieGrade movieGrade, LocalDateTime movieReleaseAt, String movieImageUrl, Long runningTimeMinutes, MovieGenre movieGenre, String theaterName, LocalDateTime movieStartAt) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieGrade = movieGrade;
        this.movieReleaseAt = movieReleaseAt;
        this.movieImageUrl = movieImageUrl;
        this.runningTimeMinutes = runningTimeMinutes;
        this.movieGenre = movieGenre;
        this.theaterName = theaterName;
        this.movieStartAt = movieStartAt;
    }

    public MovieModel toModel(){
        return MovieModel.builder()
                .movieId(movieId)
                .movieName(movieName)
                .movieGrade(movieGrade)
                .movieReleaseAt(movieReleaseAt)
                .movieImageUrl(movieImageUrl)
                .runningTimeMinutes(runningTimeMinutes)
                .movieGenre(movieGenre)
                .theaterName(theaterName)
                .movieStartAt(movieStartAt)
                .build();
    }
}
