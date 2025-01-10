package com.movie.movieapi.interfaces.movie.dto;

import com.movie.moviedomain.movie.dto.ScheduleInfo;

import java.util.List;

public class ScheduleDto {

    public record Response(
        Long id, TheaterDto.Response theater,
        ScreenDto.Response screen, MovieDto.Response movie,
        List<TimeTableDto.Response> timeTables
    ) {
        public static Response of(Long id, TheaterDto.Response theater,
                                  ScreenDto.Response screen, MovieDto.Response movie,
                                  List<TimeTableDto.Response> timeTables) {
            return new Response(id, theater, screen, movie, timeTables);
        }

        public static Response from(ScheduleInfo.Get info) {
            return Response.of(
                    info.id(), TheaterDto.Response.from(info.theater()),
                    ScreenDto.Response.from(info.screen()), MovieDto.Response.from(info.movie()),
                    info.timeTables().stream().map(TimeTableDto.Response::from).toList()
            );
        }
    }

}
