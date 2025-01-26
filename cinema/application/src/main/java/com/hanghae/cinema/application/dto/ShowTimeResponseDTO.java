package com.hanghae.cinema.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShowTimeResponseDTO {
    private Long id;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private TheaterResponseDTO theater;
}
