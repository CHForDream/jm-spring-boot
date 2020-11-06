package com.game.core.cache;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CacheType {
	public static final int COMMON = 0;// 默认类型
	public static final int ROLE_MULTI_MAPPING = 1;// 玩家一对多
	public static final int NO_CACHE = 2;// 无缓存

	/**
	 * 是否是玩家一对多的集合实体
	 * 
	 * @return
	 */
	int type() default COMMON;
}
