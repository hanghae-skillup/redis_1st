package com.bmsnc.applicaion.port.in;

import com.bmsnc.common.dto.MovieGenre;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RunningMovieCommand {

    private Long theaterId;
    private String movieName;
    private MovieGenre movieGenre;
}
