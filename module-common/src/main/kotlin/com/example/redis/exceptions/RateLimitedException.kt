package com.example.redis.exceptions

class RateLimitedException(val api: String, val ip: String): RuntimeException("Too Many Request for API: $api from IP: $ip") {
}