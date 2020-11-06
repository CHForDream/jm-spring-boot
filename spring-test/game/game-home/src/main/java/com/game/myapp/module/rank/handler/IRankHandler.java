package com.game.myapp.module.rank.handler;

import java.util.Map;
import java.util.TreeMap;

import com.game.myapp.module.rank.RankInfo;
import com.game.myapp.module.rank.RankType;

public interface IRankHandler {
	/**
	 * 添加排行数据
	 * 
	 * @param rankType    排行类型
	 * @param subRankType 子排行类型
	 * @param rankInfo    排行数据
	 * @return 排行位置. <b>-1</b>代表未上榜
	 */
	int addRankInfo(RankType rankType, String subRankType, RankInfo rankInfo);

	/**
	 * 同步redis中的排行数据到本地缓存
	 * 
	 * @param rankType         排行类型
	 * @param subRankTypeArray 子排行类型数组
	 * @param rankDataMap      接收数据的Map
	 * @param updateTimeMap    对应数据更新时间的Map
	 */
	void syncRankInfoFromRedis(RankType rankType, String[] subRankTypeArray, Map<String, TreeMap<Integer, RankInfo>> rankDataMap,
			Map<String, Long> updateTimeMap);

	/**
	 * 根据分数获得该分数能获得排名
	 * 
	 * @param rankType
	 * @param subRankType
	 * @param score
	 * @return
	 */
	int getRankByScore(RankType rankType, String subRankType, long score);

	/**
	 * 清除Redis数据库中的排行数据
	 * 
	 * @param rankType    排行类型
	 * @param subRankType 子排行类型
	 */
	void clearData(RankType rankType, String subRankType);

	/**
	 * 获取默认子排行榜类型
	 * 
	 * @return 默认子排行榜类型
	 */
	String getDefalutSubType();
}
