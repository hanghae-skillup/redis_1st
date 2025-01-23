package com.movie.domain.movie.domain;

import com.movie.common.enums.AxisX;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat {

    private Long id;
    private Long screenId;
    private AxisX axisX;
    private Integer axiosY;

}
