package com.game.myapp.module.rank.handler;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.game.constants.Loggers;
import com.game.core.redis.RedisConnFactory;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.rank.RankInfo;
import com.game.myapp.module.rank.RankType;
import com.google.common.collect.Maps;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

public class LadderRankHandler implements IRankHandler {

	@Override
	public int addRankInfo(RankType rankType, String subRankType, RankInfo rankInfo) {
		Jedis jedis = RedisConnFactory.getGameJedis();
		try {
			// 分数存负数
			rankInfo.setScore(-Math.abs(rankInfo.getScore()));

			String key = rankType.getRedisKey(subRankType);
			long minScore = Long.MIN_VALUE;
			Long rankCount = jedis.zcard(key);
			if (rankCount >= rankType.getTopNum()) {
				Set<Tuple> zrevrangeWithScores = jedis.zrevrangeWithScores(key, 0, 0);
				for (Tuple tuple : zrevrangeWithScores) {
					minScore = (long) Math.max(minScore, tuple.getScore());
				}

				// 排行榜已满, 分数小于最小分数, 则未上榜
				if (Math.abs(rankInfo.getScore()) <= Math.abs(minScore)) {
					return -1;
				}
			}

			// 添加数据
			String value = RankInfo.toJson(rankInfo);
			jedis.zadd(key, rankInfo.getScore(), value);

			// 获取排行. zrank返回的是index值, 从0开始, 排行值要在zrank的基础上+1
			long index = jedis.zrank(key, value);
			Loggers.rankLogger.info("addRankInfo(): Score: " + Math.abs(rankInfo.getScore()) + ", rank = " + ((int) index + 1));
			return (int) index + 1;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	@Override
	public void syncRankInfoFromRedis(RankType rankType, String[] subRankTypeArray, Map<String, TreeMap<Integer, RankInfo>> rankDataMap,
			Map<String, Long> updateTimeMap) {
		Jedis jedis = RedisConnFactory.getGameJedis();

		try {
			long now = System.currentTimeMillis();
			for (String cityCode : subRankTypeArray) {
				TreeMap<Integer, RankInfo> dataTreeMap = Maps.newTreeMap();
				String key = rankType.getRedisKey(cityCode);

				// 检测删除榜底排行数据
				Long rankCount = jedis.zcard(key);
				if (rankCount > rankType.getTopNum()) {
					Long deleteCount = jedis.zremrangeByRank(key, rankType.getTopNum(), rankCount - 1);
					Loggers.rankLogger.info("Delete rank bottom! rankType = " + rankType + ", cityCode = " + cityCode + ", rankCount = " + rankCount
							+ ", deleteCount = " + deleteCount);
				}

				// 读取最高的排名, 分数存的负数, 正序排序(分数绝对值高到低)
				Set<Tuple> resultSet = jedis.zrangeWithScores(key, 0, rankType.getTopNum());
				int rank = 1;// 分值最高的为第一名
				for (Tuple tuple : resultSet) {
					String element = tuple.getElement();
//				byte[] binaryElement = tuple.getBinaryElement();
					RankInfo rankInfo = RankInfo.fromJson(element);
					rankInfo.setScore(Math.abs(rankInfo.getScore()));// 负数分数是转为正数
					rankInfo.setRank(rank);
					dataTreeMap.put(rank, rankInfo);
					rank++;
				}
				rankDataMap.put(cityCode, dataTreeMap);
				updateTimeMap.put(cityCode, now);
			}
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	@Override
	public int getRankByScore(RankType rankType, String subRankType, long score) {
		Jedis jedis = RedisConnFactory.getGameJedis();
		try {
			long count = jedis.zcount(rankType.getRedisKey(subRankType), Long.MIN_VALUE, -Math.abs(score));
			Loggers.rankLogger.info("getRankByScore(): Score = " + Math.abs(score) + ", Rank = " + ((int) count + 1));
			int rank = (int) count + 1;
			if (rank > rankType.getTopNum()) {
				return -1;
			}
			return rank;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	@Override
	public void clearData(RankType rankType, String subRankType) {
		Jedis jedis = RedisConnFactory.getGameJedis();
		try {
			jedis.del(rankType.getRedisKey(subRankType));
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	@Override
	public String getDefalutSubType() {
		return GameGlobals.configManager.getLadderDefaultOpenArea();
	}
}
