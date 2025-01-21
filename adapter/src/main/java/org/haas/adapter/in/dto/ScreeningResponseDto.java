package org.haas.adapter.in.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ScreeningResponseDto {

    private Long screeningId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
//    private ThaterDto thaterDto;
}
