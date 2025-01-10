package com.movie.moviedomain.movie.dto;

import com.movie.moviedomain.movie.domain.Screen;

public class ScreenInfo {

    public record Get(Long id, Long theaterId, String name) {
        public static Get of(Long id, Long theaterId, String name) {
            return new Get(id, theaterId, name);
        }

        public static Get from(Screen screen) {
            return Get.of(screen.getId(), screen.getTheaterId(), screen.getName());
        }
    }

}
