package com.movie.common.ratelimit;

import java.util.concurrent.TimeUnit;

public interface RateLimiter {
    boolean tryAcquire(String key);
    void setRate(String key, int permits, int interval, TimeUnit unit);
} 