package org.example.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.annotaion.ValidEnum;
import org.example.domain.movie.Genre;

@Setter
@Getter
@AllArgsConstructor
public class MoviesFilterRequestDto {
    @Size(max = 255)
    private String movieTitle;

    @ValidEnum(enumClass = Genre.class, message = "장르는 다음 중 하나여야 합니다: ACTION, ROMANCE, HORROR, SF")
    private String genre;

    private boolean playing;
}
