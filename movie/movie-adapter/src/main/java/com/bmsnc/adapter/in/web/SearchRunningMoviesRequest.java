package com.bmsnc.adapter.in.web;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchRunningMoviesRequest {

    @NotNull
    private Long theaterId;
    @Size(max = 255)
    private String movieName;
    private String movieGenre;
}
