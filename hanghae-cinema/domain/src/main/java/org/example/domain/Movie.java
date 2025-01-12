package org.example.domain;

import lombok.Builder;
import lombok.Getter;
import org.example.enums.AgeRating;
import org.example.enums.Genre;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class Movie {
    private Long id;
    private String title;
    private Genre genre;
    private LocalDate releaseDate;
    private Integer runtimeMinutes;
    private AgeRating ageRating;
    private String thumbnailUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}