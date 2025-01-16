package com.movie.storage.movie.mapper;

import com.movie.domain.movie.domain.Theater;
import com.movie.storage.movie.entity.TheaterEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TheaterMapper {

    public static Theater from(TheaterEntity entity) {
        return Theater.of(entity.getId(), entity.getName());
    }

}
