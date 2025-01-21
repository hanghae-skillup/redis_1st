package com.example.domain.model.projection;

import java.time.LocalTime;

public interface ScreeningProjection {
    LocalTime getStartTime();
    LocalTime getEndTime();
    TheaterProjection getTheater();
}
