package com.movie.moviestorage.movie.mapper;

import com.movie.moviedomain.movie.domain.Screen;
import com.movie.moviestorage.movie.entity.ScreenEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScreenMapper {

    public static Screen from(ScreenEntity screenEntity) {
        return Screen.of(
                screenEntity.getId(), screenEntity.getName(),
                screenEntity.getTheaterId()
        );
    }

}
