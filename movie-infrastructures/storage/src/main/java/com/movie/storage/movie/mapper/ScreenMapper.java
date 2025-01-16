package com.movie.storage.movie.mapper;

import com.movie.domain.movie.domain.Screen;
import com.movie.storage.movie.entity.ScreenEntity;
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
