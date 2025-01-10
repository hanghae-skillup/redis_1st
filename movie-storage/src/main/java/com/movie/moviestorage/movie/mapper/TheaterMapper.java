package com.movie.moviestorage.movie.mapper;

import com.movie.moviedomain.movie.domain.Theater;
import com.movie.moviestorage.movie.entity.TheaterEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TheaterMapper {

    public static Theater from(TheaterEntity entity) {
        return Theater.of(entity.getId(), entity.getName());
    }

}
