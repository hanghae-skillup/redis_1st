package module.cache.managers;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.RequiredArgsConstructor;
import module.cache.CacheType;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class Redis {

	private final RedissonConnectionFactory connectionFactory;
	@Bean
	public RedisCacheManager redisCacheManager() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule()); // Java 8 날짜/시간 모듈 등록

		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
			.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
			.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
				new GenericJackson2JsonRedisSerializer(objectMapper)));

		Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
		Arrays.stream(CacheType.values())
			.forEach(cacheType -> redisCacheConfigurationMap.put(cacheType.getCacheName(),
				redisCacheConfiguration.entryTtl(Duration.ofMinutes(cacheType.getExpireAfterWriteMinutes()))));

		return RedisCacheManager.RedisCacheManagerBuilder
			.fromConnectionFactory(connectionFactory)
			.cacheDefaults(redisCacheConfiguration)
			.withInitialCacheConfigurations(redisCacheConfigurationMap)
			.build();
	}

}
