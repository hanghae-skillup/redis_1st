package org.example.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Reservation {
    private Long id;
    private Screening screening;
    private String userId;
    private String seatNumber;
    private LocalDateTime reservedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}