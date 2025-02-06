package com.example.config.ratelimit;

public interface RateLimit {

    boolean isMovieSearchAllowed(String ip);

    boolean isReservationAllowed(Long memberId, Long screeningId);

}
