package com.hanghae.cinema.application;

import lombok.Getter;

@Getter
public enum CacheType {
    MOVIE_TITLE_GENRE("movie_title_genre");

    private final String name;
    private final Integer expireAfterWrite;
    private final Integer maximumSize;


    CacheType(String name) {
        this.name = name;
        this.expireAfterWrite = ConstConfig.DEFAULT_TIL_SEC;
        this.maximumSize = ConstConfig.DEFAULT_MAX_SIZE;
    }

    static class ConstConfig {
        static final Integer DEFAULT_TIL_SEC = 600;
        static final Integer DEFAULT_MAX_SIZE = 10240;
    }
}
