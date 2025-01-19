package com.example.demo

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
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

        // 1) ObjectMapper 생성
        val objectMapper = ObjectMapper()
            .registerModule(KotlinModule())
            .registerModule(JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

        // 2) Jackson에 "타입 정보"를 넣도록 설정
        val ptv = BasicPolymorphicTypeValidator.builder()
            .allowIfSubType(Any::class.java)
            .build()

        // 모든 NON_FINAL 타입에 @class 정보를 심어주도록 설정
        objectMapper.activateDefaultTyping(
            ptv,
            ObjectMapper.DefaultTyping.NON_FINAL,
            JsonTypeInfo.As.PROPERTY
        )

        // 3) Redis Serializer 생성
        val jackson2JsonRedisSerializer = GenericJackson2JsonRedisSerializer(objectMapper)

        // 4) RedisTemplate 설정
        val template = RedisTemplate<String, Any>()
        template.setConnectionFactory(factory)

        template.keySerializer = StringRedisSerializer()
        template.hashKeySerializer = StringRedisSerializer()

        template.valueSerializer = jackson2JsonRedisSerializer
        template.hashValueSerializer = jackson2JsonRedisSerializer

        template.afterPropertiesSet()
        return template
    }
}
