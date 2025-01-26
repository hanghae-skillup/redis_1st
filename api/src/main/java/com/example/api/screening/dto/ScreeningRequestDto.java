package com.example.api.screening.dto;

import com.example.domain.movie.entity.Movie;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ScreeningRequestDto {

    private String title;

    @Pattern(
            regexp = "ACTION|ROMANCE|HORROR|SF",
            message = "Genre must be one of the following: ACTION, ROMANCE, HORROR, SF"
    )
    private Movie.Genre genre;
}
