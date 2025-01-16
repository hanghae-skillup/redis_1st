package com.example.movie.dto;

import lombok.Getter;

@Getter
public enum GenreDto {
    ACTION("액션"),
    SF("SF"),
    ROMANCE("로맨스"),
    HORROR("호러");

    private final String description;

    GenreDto(String description) {
        this.description = description;
    }

    public static boolean isValidGenre(String value) {
        for (GenreDto genre : GenreDto.values()) {
            System.out.println(genre.name());
            if (genre.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
