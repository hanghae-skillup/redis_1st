package com.movie.domain.dto;

public interface MovieProjection {
    Long getId();
    String getTitle();
    String getThumbnail();
    Integer getRunningTime();
    String getGenre();
} 