package com.bmsnc.applicaion.port.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RunningMovieCommand {

    private Long theaterId;
}
