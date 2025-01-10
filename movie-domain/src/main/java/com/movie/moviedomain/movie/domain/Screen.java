package com.movie.moviedomain.movie.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Screen {

    private Long id;
    private String name;       // 스크린 넘버, ex) 1관, 2관...
    private Long theaterId;

    public Screen(Long id, String name, Long theaterId) {
        this.id = id;
        this.name = name;
        this.theaterId = theaterId;
    }

    public static Screen of(Long id, String name, Long theaterId) {
        return new Screen(id, name, theaterId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Screen screen)) return false;
        return id != null && id.equals(screen.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
