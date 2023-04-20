package com.ash.login.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 支持时间限制的缓存
 * @author Wu dz
 * @date 2023/4/19
 */
public class TimeCache {

	private static Cache<String, Timer> cache;

	private TimeCache(){}
	
	static {
		cache = CacheBuilder.newBuilder()
				// 缓存时间最长24小时
				.expireAfterWrite(24, TimeUnit.HOURS)
				// 初始化容量
				.initialCapacity(100)
				// 最大容量
				.maximumSize(1000).build();
	}
	
	/**
	 * 放入对像
	 * @param key 键
	 * @param value 值
	 */
	public static void put(String key, Object value) {
		put(key, value, null);
	}
	
	/**
	 * 放入对象，并指定存放时间
	 * @param key 键
	 * @param value 值
	 * @param duration 时间值
	 * @param unit 时间单位
	 */
	public static void put(String key, Object value, long duration, TimeUnit unit) {
		long millis = unit.toMillis(duration);
		long current = System.currentTimeMillis();
		cache.put(key, new Timer(value, new Date(millis + current)));
		
	}

	/**
	 * 放入对象，并指定到期时间
	 * @param key 键
	 * @param value 值
	 * @param expired 到期时间
	 */
	public static void put(String key, Object value, Date expired) {
		cache.put(key, new Timer(value, expired));
	}
	
	/**
	 * 取出
	 * @param key 键
	 * @return 值
	 */
	public static Object get(String key) {
		if(StringUtils.isEmpty(key)) {
			return null;
		}
		
		Timer timer = cache.getIfPresent(key);
		if(null == timer) {
			return null;
		}
		
		Date expired = timer.getExpired();
		Object data = timer.getData();
		if(null == expired) {
			return data;
		}
		
		if(expired.before(new Date())) {
			cache.invalidate(key);
			return null;
		}
		return data;
	}
	
	/**
	 * 移除
	 * @param key 键
	 */
	public static void delete(String key) {
		if(StringUtils.isEmpty(key)) {
			return;
		}
		cache.invalidate(key);
	}



	/**
	 * 支持时间的缓存对象
	 * @author Wu dz
	 * @date 2023/4/19
	 */
	public static class Timer {

		/**
		 * 数据
		 */
		private Object data;
		/**
		 * 到期时间
		 */
		private Date expired;

		public Timer(Object data, Date expired) {
			super();
			this.data = data;
			this.expired = expired;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

		public Date getExpired() {
			return expired;
		}

		public void setExpired(Date expired) {
			this.expired = expired;
		}
	}
}
