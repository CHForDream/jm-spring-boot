package com.game.core.cache;

import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.Table;

import org.apache.log4j.Logger;

import com.game.constants.Loggers;
import com.game.core.hibernate.key.EntityUtils;
import com.game.core.hibernate.orm.BaseEntity;
import com.game.global.Globals;
import com.google.common.collect.Maps;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class EhcacheUtil {
	public static final String CACHE_TYPE_ALL = "All";
	public static final String CACHE_TYPE_ROLE = "Role";
	public static final String CACHE_TYPE_HERO = "Hero";
	public static final String CACHE_TYPE_ITEM = "Item";
	public static final String CACHE_TYPE_HOME = "Home";
	public static final String CACHE_TYPE_ACHTASK = "AchTask";

	private static final String[] CACHE_TYPE_ARRAY = { CACHE_TYPE_ALL, CACHE_TYPE_ROLE, CACHE_TYPE_HERO, CACHE_TYPE_ITEM, CACHE_TYPE_HOME, CACHE_TYPE_ACHTASK };

	private static final String path = "/ehcache.xml";

	private static final Map<Class, String> ENTITY_CACHE_MAP = Maps.newHashMap();

	// 缓存定时清理周期(1个周期30s, 设置为10即300s清除一次)
	private static int CACHE_CLEAR_SAMPLE_COUNT = 10;
	// 缓存定时清理周期计数器
	private static int CACHE_CLEAR_COUNT = 0;
	// 缓存定时清理周期(1个周期30s, 设置为10即300s打印一次)
	private static int CACHE_PRINT_SAMPLE_COUNT = 10;
	// 缓存打印信息计数器
	private static int CACHE_PRINT_COUNT = 0;

	private Logger log = Logger.getLogger(EhcacheUtil.class);

	private URL url;

	private CacheManager manager;

	private Map<String, Object> tableDefineClassMap = Maps.newHashMap();

	private static EhcacheUtil ehCache;

	public static EhcacheUtil getInstance() {
		if (ehCache == null) {
			ehCache = new EhcacheUtil(path);
		}
		return ehCache;
	}

	private EhcacheUtil(String path) {
		url = getClass().getResource(path);
		manager = CacheManager.create(url);
	}

	public static void register(Class clazz, String cacheType) {
		ENTITY_CACHE_MAP.put(clazz, cacheType);
	}

	public static String getCacheType(Class clazz) {
		return ENTITY_CACHE_MAP.getOrDefault(clazz, CACHE_TYPE_ALL);
	}

	public static void printCacheStatus() {
		boolean isCheckCache = CACHE_CLEAR_COUNT % CACHE_CLEAR_SAMPLE_COUNT == 0;
		CACHE_CLEAR_COUNT++;
		boolean isPrintLog = CACHE_PRINT_COUNT % CACHE_PRINT_SAMPLE_COUNT == 0;
		CACHE_PRINT_COUNT++;

		StringBuilder sb = new StringBuilder();
		if (isPrintLog) {
			sb.append("CacheSize ->");
		}
		for (int i = 0; i < CACHE_TYPE_ARRAY.length; i++) {
			Cache cache = getInstance().manager.getCache(CACHE_TYPE_ARRAY[i]);
			// 先驱逐过期元素
			cache.evictExpiredElements();
			if (isCheckCache) {
				// 清除过期的缓存, 失效的缓存在调用cache.get()时会被自动删除
				Iterator iterator = cache.getKeys().iterator();
				while (iterator.hasNext()) {
					cache.get(iterator.next());
				}
			}

			if (isPrintLog) {
				sb.append(" [").append(CACHE_TYPE_ARRAY[i]).append(" : ").append(cache.getSize()).append("]");
			}
		}

		if (isPrintLog) {
			Loggers.cacheLogger.info(sb.toString());
		}
	}

	public void put(String cacheName, Object key, Object value) {
		Cache cache = manager.getCache(cacheName);
		cache.put(new Element(key, value));
	}

	public Object get(String cacheName, Object key) {
		Cache cache = manager.getCache(cacheName);
		Element element = cache.get(key);
		return element == null ? null : element.getObjectValue();
	}

	public void remove(String cacheName, Object key) {
		Cache cache = manager.getCache(cacheName);
		cache.remove(key);
	}

	public void clearAll(long uid) {
		if (tableDefineClassMap.size() == 0) {
			tableDefineClassMap.putAll(Globals.applicationContext.getBeansWithAnnotation(Table.class));
		}

		for (Object beanClassName : tableDefineClassMap.values()) {
			String cacheName = ENTITY_CACHE_MAP.getOrDefault(beanClassName.getClass(), EhcacheUtil.CACHE_TYPE_ALL);
			try {
				String key = EntityUtils.getEntityUidKey((Class<? extends BaseEntity>) beanClassName.getClass(), uid);
				remove(cacheName, key);
			} catch (Exception ex) {
				ex.printStackTrace();
				log.error("Error entity key! class = " + beanClassName.getClass().getSimpleName() + ", id = " + uid);
			}
		}
	}
}