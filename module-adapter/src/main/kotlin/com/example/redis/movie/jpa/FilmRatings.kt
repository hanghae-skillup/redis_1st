package com.example.redis.movie

enum class FilmRatings(description: String) {
    MPAA_GPG("ALL"),
    MPAA_PG_13("12세 이상 관람가"),
    MPAA_PG_13R("15세 이상 관람가"),
    MPAA_R_NC_17("청소년 관람불가"),
    MPAA_NC_17("제한상영가")
}