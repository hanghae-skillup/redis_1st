package com.movie.movieapi.interfaces.movie.dto;

import com.movie.moviedomain.enums.Genre;
import com.movie.moviedomain.movie.dto.command.ScheduleCommand;
import com.movie.moviedomain.movie.dto.info.ScheduleInfo;
import jakarta.validation.constraints.Size;

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

    public record Search(
            @Size(max = 100, message = "영화제목은 100자를 넘을수 없습니다.") String movieName,
            Genre genre
    ) {
        public static Search of(String movieName, Genre genre) {
            return new Search(movieName, genre);
        }

        public ScheduleCommand.Search search() {
            return ScheduleCommand.Search.of(movieName, genre);
        }
    }

}
