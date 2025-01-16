package com.movie.moviedomain.movie.dto.info;

import com.movie.moviedomain.movie.domain.Theater;

public class TheaterInfo {

    public record Get(Long id, String name) {
        public static Get of(Long id, String name) {
            return new Get(id, name);
        }

        public static Get from(Theater theater) {
            return Get.of(theater.getId(), theater.getName());
        }
    }

}
