package com.example.demo

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.databind.SerializationFeature
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig {

    @Bean
    fun redisTemplate(factory: RedisConnectionFactory): RedisTemplate<String, Any> {

        // 1) ObjectMapper 생성 후, JavaTimeModule 등록
        val objectMapper = ObjectMapper()
            .registerModule(JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        // 위 disable 설정은 "timestamp" 대신 ISO-8601 문자열로 날짜/시간을 표현하도록 해줍니다.

        // 2) Jackson 기반 Redis Serializer 생성
        val jackson2JsonRedisSerializer = GenericJackson2JsonRedisSerializer(objectMapper)

        val template = RedisTemplate<String, Any>()
        template.setConnectionFactory(factory)

        // 3) key, hashKey는 String Serializer 사용
        template.keySerializer = StringRedisSerializer()
        template.hashKeySerializer = StringRedisSerializer()

        // 4) value, hashValue는 우리가 만든 Jackson Serializer 사용
        template.valueSerializer = jackson2JsonRedisSerializer
        template.hashValueSerializer = jackson2JsonRedisSerializer

        template.afterPropertiesSet()
        return template
    }
}
