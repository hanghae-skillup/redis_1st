package com.movie.movieapi.interfaces.movie.dto;

import com.movie.moviedomain.movie.dto.ScreenInfo;

public class ScreenDto {

    public record Response(Long id, Long theaterId, String name) {
        public static Response of(Long id, Long theaterId, String name) {
            return new Response(id, theaterId, name);
        }

        public static Response from(ScreenInfo.Get info) {
            return Response.of(info.id(), info.theaterId(), info.name());
        }
    }

}
