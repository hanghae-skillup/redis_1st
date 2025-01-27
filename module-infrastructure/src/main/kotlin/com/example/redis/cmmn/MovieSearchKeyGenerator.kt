package com.example.redis.cmmn

import org.springframework.cache.interceptor.KeyGenerator
import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Component("movieSearchKeyGenerator")
class MovieSearchKeyGenerator: KeyGenerator {
    override fun generate(target: Any, method: Method, vararg params: Any?): Any {
        val title = params.getOrNull(0)?.toString() ?: "none"
        val genre = params.getOrNull(1)?.toString() ?: "none"
        return "title:$title:genre:$genre"
    }

}