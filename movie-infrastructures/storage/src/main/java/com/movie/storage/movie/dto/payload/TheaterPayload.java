package com.movie.storage.movie.dto.payload;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TheaterPayload {

    @Getter
    @NoArgsConstructor
    public static class Get {

        private Long id;
        private String name;

        @QueryProjection
        public Get(Long id, String name) {
            this.id = id;
            this.name = name;
        }

    }

}
