package module.service.showing;

import java.util.Objects;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShowingCacheService {

	private final CacheManager cacheManager;

	public void evictShowingCache() {
		cacheManager.getCache("movies").clear();
	}

	public void evictShowingCache(String title, Long genreId){
		for (int i = 0; i < title.length(); i++) {
			for (int j = i; j < title.length(); j++) {
				Objects.requireNonNull(cacheManager.getCache("movies"),"there is no cache named 'movies'")
					.evictIfPresent(title.substring(i,j+1) + 'G' + genreId);
			}
		}
	}

}
