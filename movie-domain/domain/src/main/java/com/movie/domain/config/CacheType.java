package com.movie.domain.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CacheType {

    SCHEDULES("schedules", ConstantConfig.DEFAULT_TTL_MIN, ConstantConfig.DEFAULT_MAX_SIZE),

    ;

    private final String cacheName;
    private final int expiredAfterWrite;      // 만료시간 없이 Envict로 캐시를 지워야 함
    //    private final int refreshAfterWrite;
    private final int maximumSize;

    public static class ConstantConfig {
        static final int DEFAULT_TTL_MIN = 10;
        static final int DEFAULT_MAX_SIZE = 500;
    }

}
