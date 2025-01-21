package com.movie.domain.movie.dto.info;

import com.movie.domain.movie.domain.Theater;

public class TheaterInfo {

    public record Get(Long id, String name) {
        public static Get of(Long id, String name) {
            return new Get(id, name);
        }

        public static Get from(Theater theater) {
            return Get.of(theater.getId(), theater.getName());
        }

//        public static Get from(TheaterPayload payload) {
//            return null;
//        }
    }

}
