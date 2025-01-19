package module.cache;

public enum CacheType {
	MOVIES("movies", 10, 100);

	private final String cacheName;
	private final int expireAfterWriteMinutes;
	private final int maximumSize;

	CacheType(String cacheName, int expireAfterWriteMinutes, int maximumSize) {
		this.cacheName = cacheName;
		this.expireAfterWriteMinutes = expireAfterWriteMinutes;
		this.maximumSize = maximumSize;
	}

	public String getCacheName() {
		return cacheName;
	}

	public int getExpireAfterWriteMinutes() {
		return expireAfterWriteMinutes;
	}

	public int getMaximumSize() {
		return maximumSize;
	}
}
