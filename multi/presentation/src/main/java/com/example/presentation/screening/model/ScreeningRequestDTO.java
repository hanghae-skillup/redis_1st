package com.example.presentation.screening.model;

import com.example.domain.screening.Screening;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ScreeningRequestDTO {

    private Long movieId;

    private Long theaterId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime screeningDate;

    public static Screening of(ScreeningRequestDTO screeningRequestDTO){
        return Screening.builder()
                .startTime(screeningRequestDTO.startTime)
                .endTime(screeningRequestDTO.endTime)
                .screeningDate(screeningRequestDTO.screeningDate)
                .build();
    }

}
