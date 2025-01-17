package com.bmsnc.common.dto;

public enum MovieGenre {
    COMEDY,
    FANTASY,
    ROMANCE,
    ACTION,
    ANIMATION,
    HORROR,
    ETC;

    public static boolean anyMatch(String genre) {
        for (MovieGenre movieGenre : MovieGenre.values()) {
            if (movieGenre.name().equalsIgnoreCase(genre)) {
                return true;
            }
        }
        return false;
    }
}

