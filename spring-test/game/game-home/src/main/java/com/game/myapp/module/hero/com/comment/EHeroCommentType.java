package com.game.myapp.module.hero.com.comment;

import java.util.Map;

import com.game.core.redis.RedisConnFactory;
import com.google.common.collect.Maps;

public enum EHeroCommentType {
	/** 英雄时间榜 */
	HERO_TIME(EComment.HERO, ECommentRankType.TIME, "_hero_time"),
	/** 英雄热度榜 */
	HERO_FABULOUS(EComment.HERO, ECommentRankType.FABULOUS, "_hero_fabulous"),
	/** 宠物时间榜 */
	PETS_TIME(EComment.PETS, ECommentRankType.TIME, "_pets_time"),
	/** 宠物热度榜 */
	PETS_FABULOUS(EComment.PETS, ECommentRankType.FABULOUS, "_pets_fabulous");

	private static Map<EComment, Map<ECommentRankType, EHeroCommentType>> values = Maps.newHashMap();
	static {
		for (EHeroCommentType value : EHeroCommentType.values()) {
			if (values.containsKey(value.getType())) {
				values.get(value.getType()).put(value.getRankType(), value);
			} else {
				Map<ECommentRankType, EHeroCommentType> map = Maps.newHashMap();
				map.put(value.getRankType(), value);
				values.put(value.getType(), map);
			}
		}
	}

	public static EHeroCommentType valueOf(EComment type, ECommentRankType rankType) {
		return values.get(type).get(rankType);
	}

	/** 类型 */
	private final EComment type;
	/** 榜单类型 */
	private final ECommentRankType rankType;
	/** key后缀 */
	private final String key;

	private EHeroCommentType(EComment type, ECommentRankType rankType, String key) {
		this.type = type;
		this.rankType = rankType;
		this.key = key;
	}

	public String getRedisKey(String code) {
		return RedisConnFactory.GAME_FOLDER_COMMENT + ":" + key + "_" + code;
	}

	public String getRedisKey(int code) {
		return RedisConnFactory.GAME_FOLDER_COMMENT + ":" + key + "_" + code;
	}

	public EComment getType() {
		return type;
	}

	public ECommentRankType getRankType() {
		return rankType;
	}
}
