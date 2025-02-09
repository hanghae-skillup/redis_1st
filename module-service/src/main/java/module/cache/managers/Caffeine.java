package module.cache.managers;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import module.cache.CacheType;

// @Configuration
// @EnableCaching
public class Caffeine {

	@Bean
	public CaffeineCacheManager caffeineCacheManager(){
		CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();

		Arrays.stream(CacheType.values())
			.forEach(cacheType -> caffeineCacheManager.registerCustomCache(
				cacheType.getCacheName(),
				new CaffeineCache(
					cacheType.getCacheName(),
					com.github.benmanes.caffeine.cache.Caffeine.newBuilder()
						.expireAfterAccess(cacheType.getExpireAfterWriteMinutes(), TimeUnit.MINUTES)
						.maximumSize(cacheType.getMaximumSize())
						.build()
				).getNativeCache()
			));

		return caffeineCacheManager;
	}
}
