package com.example.app.movie.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record SearchMovies (
        @NotNull(message = "상영 날짜는 필수에요")
        LocalDate showDate
){
}
