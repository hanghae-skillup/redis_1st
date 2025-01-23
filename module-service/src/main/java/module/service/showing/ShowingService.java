package module.service.showing;

import java.util.List;
import java.util.Objects;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.stereotype.Service;

import dto.movie.MovieShowingResponse;
import lombok.RequiredArgsConstructor;
import module.repository.showing.ShowingRepository;

@Service
@RequiredArgsConstructor
public class ShowingService {

	private final ShowingRepository showingRepository;
	private final CacheManager cacheManager;

	@Cacheable(cacheNames = "movies", key = "#title + 'G' + #genreId")
	public List<MovieShowingResponse> getTodayShowing(String title, Long genreId) {
		return showingRepository.getShowingByMovieTitleAndGenre(title, genreId);
	}

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
