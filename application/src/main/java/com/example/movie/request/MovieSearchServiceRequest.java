package com.example.movie.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MovieSearchServiceRequest {
    private String title;
    private String genre;

    @Builder
    public MovieSearchServiceRequest(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }
}
