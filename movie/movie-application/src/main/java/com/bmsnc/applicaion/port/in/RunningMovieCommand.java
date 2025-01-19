package com.bmsnc.applicaion.port.in;

import com.bmsnc.common.dto.MovieGenre;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RunningMovieCommand {

    @NotNull
    private Long theaterId;
    @Size(max = 255)
    private String movieName;
    private MovieGenre movieGenre;
}
