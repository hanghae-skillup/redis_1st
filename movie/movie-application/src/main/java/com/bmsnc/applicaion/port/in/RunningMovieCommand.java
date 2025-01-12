package com.bmsnc.applicaion.port.in;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class RunningMovieCommand {

    private Long theaterId;
    private LocalDateTime now;
}
