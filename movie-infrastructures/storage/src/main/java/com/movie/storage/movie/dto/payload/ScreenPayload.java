package com.movie.storage.movie.dto.payload;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ScreenPayload {

    @Getter
    @NoArgsConstructor
    public static class Get {

        private Long id;
        private String name;
        private Long theaterId;

        @QueryProjection
        public Get(Long id, String name, Long theaterId) {
            this.id = id;
            this.name = name;
            this.theaterId = theaterId;
        }
    }

}
