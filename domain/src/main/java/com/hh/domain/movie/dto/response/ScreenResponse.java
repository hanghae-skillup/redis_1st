package com.hh.domain.movie.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hh.domain.movie.dto.ScreenDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Getter
@AllArgsConstructor
public class ScreenResponse {

    private Integer id;

    private String name;

    private Integer movieId;

    private Integer theaterId;

    protected String startTime;

    private String endTime;

    public static ScreenResponse from(ScreenDto dto) {

        return new ScreenResponse(
                dto.getId(),
                dto.getName(),
                dto.getMovieId(),
                dto.getTheaterId(),
                dto.getStartTime(),
                dto.getEndTime()
        );
    }
}
