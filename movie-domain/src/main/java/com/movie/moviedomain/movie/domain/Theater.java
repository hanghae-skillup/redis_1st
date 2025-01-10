package com.movie.moviedomain.movie.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Theater {

    private Long id;
    private String name;
    private List<Screen> schedules;

    public Theater(Long id, String name, List<Screen> schedules) {
        this.id = id;
        this.name = name;
        this.schedules = schedules;
    }

    public static Theater of(Long id, String name) {
        return new Theater(id, name, null);
    }
}
