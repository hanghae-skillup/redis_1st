package org.example.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class MoviesFilterRequestDto implements Serializable {
    @Size(max = 255)
    private String movieTitle;

    @Pattern(
            regexp = "|(?i)ACTION|ROMANCE|HORROR|SF",
            message = "장르는 다음 중 하나여야 합니다: ACTION, ROMANCE, HORROR, SF"
    )
    private String genre;

    private boolean playing;
}
