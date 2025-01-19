package com.movie.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieProjection {
    private Long id;
    private String title;
    private String thumbnail;
    private Integer runningTime;
    private String genre;
} 