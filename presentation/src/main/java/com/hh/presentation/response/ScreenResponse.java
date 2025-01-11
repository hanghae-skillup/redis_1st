package com.hh.presentation.response;

import com.hh.domain.dto.MovieDto;
import com.hh.domain.dto.ScreenDto;
import com.hh.domain.dto.TheaterDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;

@Getter
@AllArgsConstructor
public class ScreenResponse {

    private String title;
    private String firmRating;
    private Date releasedDate;
    private String theaterName;
    private String thumbnail;
    private int runningTime;
    private String genre;
    private String startTime;
    private String endTime;

    public static ScreenResponse from(ScreenDto dto) {
        MovieDto movieDto = dto.getMovie();
        TheaterDto theaterDto = dto.getTheater();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");


        return new ScreenResponse(
                movieDto.getTitle(),
                dto.getMovie().getFirmRating(),
                dto.getMovie().getReleasedDate(),
                dto.getTheater().getName(),
                dto.getMovie().getThumbnail(),
                dto.getMovie().getRunningTime(),
                dto.getMovie().getGenre(),
                dto.getStartTime().format(timeFormatter),
                dto.getEndTime().format(timeFormatter)

        );
    }
}
