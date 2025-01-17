package org.example.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MoviesFilterRequestDto {
    @Size(max = 255)
    private String movieTitle;

    private String genre;
}
