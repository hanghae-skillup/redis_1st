package module.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;

import module.service.showing.ShowingCacheService;
import module.service.showing.ShowingService;

@SpringBootTest
public class ShowingCacheTest {

	ShowingService showingService;
	ShowingCacheService showingCacheService;
	ModelMapper modelMapper;
	RedisCacheManager redisCacheManager;

	@Autowired
	public ShowingCacheTest(ShowingService showingService,
		ShowingCacheService showingCacheService,
		ModelMapper modelMapper, RedisCacheManager redisCacheManager) {
		this.showingCacheService = showingCacheService;
		this.showingService = showingService;
		this.modelMapper = modelMapper;
		this.redisCacheManager = redisCacheManager;
	}

	@BeforeEach
	@DisplayName("테스트 전 캐시 제거")
	public void evictAllCacheBeforeTest() {
		redisCacheManager.getCacheNames().stream()
			.map(redisCacheManager::getCache)
			.forEach(Cache::invalidate);
	}

	@AfterEach
	@DisplayName("테스트 후 캐시 제거")
	public void evictAllCacheAfterTest() {
		redisCacheManager.getCacheNames().stream()
			.map(redisCacheManager::getCache)
			.forEach(Cache::invalidate);
	}

	@Test
	@DisplayName("injection Test")
	public void injectionTest() {
		assertNotNull(showingService);
	}

	@Test
	@DisplayName("캐시 제거 메소드 테스트 - 전체")
	public void evictCacheAll(){
		//given
		showingService.getTodayShowing(null, null);

		//when
		Assertions.assertNotNull(redisCacheManager.getCache("movies").get("nullGnull"));

		//then
		showingCacheService.evictShowingCache();
		Assertions.assertNull(redisCacheManager.getCache("movies").get("nullGnull"));
	}

	@Test
	@DisplayName("캐시 제거 메소드 테스트 - 일부")
	public void evictCacheOne(){
		//given
		List<String> titleArr = List.of("말할 수 없는 비밀", "말할 수", "없는", "비밀");
		titleArr.stream()
			.forEach(title-> showingService.getTodayShowing(title, 2L));

		// 키 생성 검증
		titleArr.stream().map(title-> redisCacheManager.getCache("movies").get(title + "G2"))
			.forEach(Assertions::assertNotNull);

		//when
		// 해당 영화의 상영정보가 업데이트 되어 캐시를 지워줘야 하는 상황
		showingCacheService.evictShowingCache("말할 수 없는 비밀", 2L);

		//then
		// 생성 되었던 키들이 모두 제거되었는지 확인
		titleArr.stream().map(title-> redisCacheManager.getCache("movies").get(title + "G2"))
			.forEach(Assertions::assertNull);
	}

}
