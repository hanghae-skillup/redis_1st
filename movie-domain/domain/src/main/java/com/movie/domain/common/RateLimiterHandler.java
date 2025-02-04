package com.movie.domain.common;

public interface RateLimiterHandler {

    boolean isRequestLimitExceeded(String ip);

    boolean isIpBlocked(String ip);

    void blockIp(String ip);

    boolean tryAcquire(String key);

}
