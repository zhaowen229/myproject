package com.chz.cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.chz.utils.PropertyUtils;

/**
 * 		// expireAfterAccess: 当缓存项在指定的时间段内没有被读或写就会被回收。
 * 		// expireAfterWrite：当缓存项在指定的时间段内没有更新就会被回收。
 * 		// refreshAfterWrite：当缓存项上一次更新操作之后的多久会被刷新
 * 		// cache = CacheBuilder.newBuilder().expireAfterWrite(30,
 * 		// TimeUnit.SECONDS).build();
 */
public class TestGuavaCache {

	private static final Logger logger = LogManager.getLogger(TestGuavaCache.class);
	private Cache<Object, Object> cache;
	private static final String CACHE_EXPIRE_TIME = "cache.expire.time.setting";

	public TestGuavaCache() {
		cache = CacheBuilder
				// from("")
				.from(PropertyUtils.getInstance().getProperties().getProperty(TestGuavaCache.CACHE_EXPIRE_TIME))
				.removalListener(listener -> {
					logger.info("guava cache clear objects.");
				}).build();

	}

	public Cache<Object, Object> putValue() {
		cache.put("key", "666");
		return cache;
	}

	/**
	 * 过期之后cache会自动缓存中的数据
	 */
	public void testCacheExpire() {
		putValue();
		System.out.println("before clear cache value ==" + cache.getIfPresent("key"));

		try {
			// expireAfterWrite=30s
			Thread.sleep(40000);
		} catch (InterruptedException e) {
		}

		System.out.println("after clear cache value ==" + cache.getIfPresent("key"));
	}

	/**
	 * 调用invalidate方面后会执行removalListener监听中的代码
	 */
	@Test
	public void testCacheInvalidate() {
		putValue();
		System.out.println("before clear cache value ==" + cache.getIfPresent("key"));

		cache.invalidate("key");

		System.out.println("after clear cache value ==" + cache.getIfPresent("key"));
	}

}
