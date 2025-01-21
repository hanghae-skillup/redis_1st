package com.movie.movieapi.interfaces.movie.dto;

import com.movie.domain.movie.dto.info.TheaterInfo;

public class TheaterDto {

    public record Response(Long id, String name) {
        public static Response of(Long id, String name) {
            return new Response(id, name);
        }
        public static Response from(TheaterInfo.Get info) {
            return Response.of(info.id(), info.name());
        }
    }

}
