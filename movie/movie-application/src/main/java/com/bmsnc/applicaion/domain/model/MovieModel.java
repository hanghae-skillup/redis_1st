package com.bmsnc.applicaion.domain.model;

import com.bmsnc.common.dto.MovieGenre;
import com.bmsnc.common.dto.MovieGrade;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MovieModel {

    private Long movieId;
    private String movieName;
    private MovieGrade movieGrade;
    private LocalDateTime movieReleaseAt;
    private String movieImageUrl;
    private Long runningTime;
    private MovieGenre movieGenre;

}
