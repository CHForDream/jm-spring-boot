package com.game.myapp.module.rank;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.game.common.user.UserBean;
import com.game.constants.ErrorCodeConst;
import com.game.constants.Loggers;
import com.game.core.handler.MsgBack;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import buffer.CGRankInfoMsg.CGRankInfoProto;
import buffer.GCRankInfoMsg.GCRankInfoProto;
import buffer.GCRankInfoMsg.GCRankInfoProto.Builder;
import buffer.GCRankInfoMsg.PlayerInfoProto;
import buffer.GCRankInfoMsg.RankInfoProto;

@SuppressWarnings("unused")
public class RankService {
	// 同步排行数据到本地的取模采样基数(1个周期30s, 设置为10即300s一次)
	private static int RANK_SYNC_DATA_SAMPLE = 10;
	// 同步排行数据到本地的计数
	private static int RANK_SYNC_DATA_COUNT = 0;

	/** 城市编码 -> Bean */
	private Set<String> cityCodeSet = Sets.newHashSet();
	/** 所有排行数据本地缓存<排行类型, <排行子榜, <名次, 数据>>> */
	private Map<RankType, Map<String, TreeMap<Integer, RankInfo>>> rankInfoMap = Maps.newConcurrentMap();
	/** 排行数据->更新时间<排行类型, <排行子榜, 上次更新时间>> */
	private Map<RankType, Map<String, Long>> rankUpdateTimeMap = Maps.newConcurrentMap();
//	/** 所有排行数据的第一名<排行类型, <排行子榜, 第一的数据>> */
//	private Map<RankType, Map<String, RankInfo>> rankTopOneMap = Maps.newConcurrentMap();

	public RankService() {
//		// 初始化城市信息
//		TreeMap<Integer, CityBean> dataMap = Datas.getDataMap(CityBean.class);
//		for (CityBean cityBean : dataMap.values()) {
//			cityCodeSet.add(cityBean.getCode());
//		}

		// 初始化Map数据结构
		initRankInfoMap();

		// 初始化排行数据
		syncRankInfoData();
	}

	public void CGRankInfo(MsgBack msgBack, UserBean userBean, CGRankInfoProto msg) {
		int type = msg.getRankType();
		String rankSubType = msg.getRankSubType();
		long time = msg.getTime();

		// 构造默认的消息内容
		GCRankInfoProto.Builder builder = buildGCRankInfoProto(msgBack, msg);

		RankType rankType = RankType.valueOf(type);
		if (rankType == null) {
			Loggers.rankLogger.warn("No rankType: " + type);
			builder.setStatus(ErrorCodeConst.RANK_ERROR_RANK_TYPE);
			return;
		}

		if (!rankUpdateTimeMap.containsKey(rankType)) {
			Loggers.rankLogger.warn("No rank update time for rankType: " + rankType);
			// 无更新时间
			return;
		}

		String realSubType = rankSubType;
		Map<String, Long> updateTimeMap = rankUpdateTimeMap.get(rankType);
		long lastUpdateTime = 0;
		if (updateTimeMap.containsKey(realSubType)) {
			lastUpdateTime = updateTimeMap.get(realSubType);
		} else {
			// 没有子榜类型, 使用默认子榜
			realSubType = rankType.getLogicalType().getHandler().getDefalutSubType();
			lastUpdateTime = updateTimeMap.getOrDefault(realSubType, time);
		}
		builder.setTime(lastUpdateTime);

		//自己的信息
//		RankInfo ownerRankInfo = BattleGlobals.ladderRankBattleManager.getRankInfoByUidAndRankType(userBean.getUid(), rankType);
//		if (ownerRankInfo != null) {
//			int rank = rankType.getLogicalType().getHandler().getRankByScore(rankType, realSubType, ownerRankInfo.getScore());
//			if (rank > 1) {
//				rank -= 1;
//			}
//			RankInfoProto.Builder owner = RankInfoProto.newBuilder();
//			owner.setRank(rank);
//			owner.setScore(ownerRankInfo.getScore());
//			for (RankPlayerInfo rankPlayerInfo : ownerRankInfo.getPlayerInfoList()) {
//				owner.addPlayerInfoList(PlayerInfoProto.newBuilder().setUid(rankPlayerInfo.getUid()).setHeader(rankPlayerInfo.getHeader())
//						.setLevel(rankPlayerInfo.getLevel()).setName(rankPlayerInfo.getName()));
//			}
//			builder.setMyRankInfo(owner);
//		}
		// 若排行榜数据未变化, 直接返回
		if (time == lastUpdateTime && time != 0) {
			return;
		}

		fillRankInfoToMsg(rankType, realSubType, builder);
	}

	/**
	 * 添加新的排行数据
	 * 
	 * @param rankType       排行类型
	 * @param subRankType    子排行类型
	 * @param score          分数
	 * @param playerInfoList 玩家信息List
	 * @return 返回玩家当前的排行数据. 排名 <b>-1</b> 代表未上榜
	 */
	public RankInfo addRankInfo(RankType rankType, String subRankType, long score, List<RankPlayerInfo> playerInfoList) {
		if (rankType == null || subRankType == null) {
			return null;
		}

		RankInfo rankInfo = new RankInfo();
		rankInfo.setScore(score);
		rankInfo.setPlayerInfoList(playerInfoList);
		int rank = rankType.getLogicalType().getHandler().addRankInfo(rankType, subRankType, rankInfo);
		rankInfo.setRank(rank);
		rankInfo.setScore(Math.abs(rankInfo.getScore()));

		syncRankInfoFromRedisToLocal(rankType);
		return rankInfo;
	}

	/**
	 * 根据排行类型, 获取前<b>topNum</b>名的排行信息
	 * 
	 * @param rankType 排行榜类型
	 * @param topNum   排行top的数量
	 * @return 排行榜前几名的RankInfo信息
	 *         <br>
	 *         出现任何异常或数据错误, 返回<b>Null</b>
	 */
	public List<RankInfo> getTopListByType(RankType rankType, String subRankType, int topNum) {
		if (rankType == null || subRankType == null) {
			Loggers.rankLogger.error("Error rankType! rankType = " + rankType + ", subRankType = " + subRankType);
			return Lists.newArrayList();
		}

		if (!rankInfoMap.containsKey(rankType)) {
			Loggers.rankLogger.error("No data for rankType! rankType = " + rankType);
			return Lists.newArrayList();
		}

		Map<String, TreeMap<Integer, RankInfo>> subRankDataMap = rankInfoMap.get(rankType);
		if (!subRankDataMap.containsKey(subRankType)) {
			subRankType = rankType.getLogicalType().getHandler().getDefalutSubType();
		}

		TreeMap<Integer, RankInfo> dataMap = subRankDataMap.get(subRankType);
		int count = 0;
		List<RankInfo> result = Lists.newArrayList();
		for (RankInfo rankInfo : dataMap.values()) {
			if (count >= topNum) {
				break;
			}
			result.add(rankInfo);
		}

		return result;
	}

	/**
	 * 根据排行类型获取玩家的排行数据
	 * 
	 * @param rankType
	 * @return 返回玩家在排行榜上的数据
	 *         <br>
	 *         若没有数据, 返回<b>null</b>
	 */
	public RankInfo getRankInfoByUid(RankType rankType, String subRankType, long uid) {
		if (!rankInfoMap.containsKey(rankType)) {
			return null;
		}

		Map<String, TreeMap<Integer, RankInfo>> subRankDataMap = rankInfoMap.get(rankType);
		if (!subRankDataMap.containsKey(subRankType)) {
			subRankType = rankType.getLogicalType().getHandler().getDefalutSubType();
		}

		TreeMap<Integer, RankInfo> treeMap = subRankDataMap.get(subRankType);
		for (RankInfo rankInfo : treeMap.values()) {
			for (RankPlayerInfo rankPlayerInfo : rankInfo.getPlayerInfoList()) {
				if (rankPlayerInfo.getUid() == uid) {
					return rankInfo;
				}
			}
		}

		return null;
	}

	public void reset(RankLogicalType rankType) {
		switch (rankType) {
		case LADDER:
			// 重置天梯排行榜
			resetLadderRank();

			break;

		default:
			break;
		}
	}

	/**
	 * 根据分数计算能获得的排名
	 * 
	 * @param rankType    排行类型
	 * @param subRankType 子排行类型
	 * @param score       分数
	 * @return 能获得的排名
	 */
	public int getRankByScore(RankType rankType, String subRankType, long score) {
		if (rankType == null || subRankType == null) {
			return -1;
		}
		return rankType.getLogicalType().getHandler().getRankByScore(rankType, subRankType, score);
	}

	public void syncRankInfoData() {
		try {
			if (RANK_SYNC_DATA_COUNT % RANK_SYNC_DATA_SAMPLE != 0) {
				return;
			}

			for (RankType rankType : RankType.values()) {
				syncRankInfoFromRedisToLocal(rankType);
			}
		} finally {
			RANK_SYNC_DATA_COUNT++;
		}
	}

	private void initRankInfoMap() {
		for (RankType rankType : RankType.values()) {
			Map<String, TreeMap<Integer, RankInfo>> subRankMap = Maps.newConcurrentMap();
			rankInfoMap.put(rankType, subRankMap);

			Map<String, Long> updateTimeMap = Maps.newConcurrentMap();
			rankUpdateTimeMap.put(rankType, updateTimeMap);
		}
	}

	/**
	 * 从Redis中读取排行榜最新数据, 更新到本地数据
	 */
	private void syncRankInfoFromRedisToLocal(RankType rankType) {
		RankLogicalType logicalType = rankType.getLogicalType();
		switch (logicalType) {
		case LADDER:
//			LadderOpenEntity entity = Globals.getEntityProxy().get(LadderOpenEntity.class, 1);
//			if (entity == null) {
//				return;
//			}
//			String[] cityCodeArray = entity.getOpenArea().split(",");
//			logicalType.getHandler().syncRankInfoFromRedis(rankType, cityCodeArray, rankInfoMap.get(rankType), rankUpdateTimeMap.get(rankType));
			break;

		default:
			break;
		}
	}

	private Builder buildGCRankInfoProto(MsgBack msgBack, CGRankInfoProto msg) {
		GCRankInfoProto.Builder builder = GCRankInfoProto.newBuilder();
		builder.setRankType(msg.getRankType());
		builder.setRankSubType(msg.getRankSubType());
		builder.setStatus(ErrorCodeConst.SUCCESS);
		builder.setTime(msg.getTime());
		msgBack.addBuilder(builder);
		return builder;
	}

	private void fillRankInfoToMsg(RankType rankType, String rankSubType, Builder builder) {
		switch (rankType) {
		case LADDER_1:
		case LADDER_2:
		case LADDER_3:
		case LADDER_4:
		case LADDER_5:
		case LADDER_6:
			fillLadderRankInfoToMsg(rankType, rankSubType, builder);
			break;

		default:
			break;
		}
	}

	private void fillLadderRankInfoToMsg(RankType rankType, String rankSubType, GCRankInfoProto.Builder msgBuilder) {
		// FIXME
		// TODO for debug
		if (!rankInfoMap.containsKey(rankType)) {
//			Map<String, TreeMap<Integer, RankInfo>> subRankMap = Maps.newConcurrentMap();
//			rankInfoMap.put(rankType, subRankMap);
//
//			testInfo(msgBuilder, rankType, rankSubType);
			return;
		}

		if (rankInfoMap.get(rankType).isEmpty()) {
			Loggers.rankLogger.error("No rank data! rankType = " + rankType);

			// TODO for debug
//			testInfo(msgBuilder, rankType, rankSubType);
			return;
		}

		int limit = 10;
		int count = 0;
		Map<String, TreeMap<Integer, RankInfo>> rankMap = rankInfoMap.get(rankType);
		if (!rankMap.containsKey(rankSubType)) {
			// 若榜单上没有该区域, 使用默认区域
			// 默认区域逻辑已在updateTime中处理过了, 这里不再处理
			//			rankSubType = GameGlobals.configManager.getLadderDefaultOpenArea();
			Loggers.rankLogger.error("No sub rank data! rankType = " + rankType + ", rankSubType = " + rankSubType);
			return;
		}

		TreeMap<Integer, RankInfo> treeMap = rankMap.get(rankSubType);
		for (RankInfo rankInfo : treeMap.values()) {
			if (count >= limit) {
				break;
			}

			RankInfoProto.Builder rankInfoBuilder = RankInfoProto.newBuilder();
			rankInfoBuilder.setRank(rankInfo.getRank());
			rankInfoBuilder.setScore(rankInfo.getScore());
			for (RankPlayerInfo playerInfo : rankInfo.getPlayerInfoList()) {
				PlayerInfoProto.Builder playerInfoBuilder = PlayerInfoProto.newBuilder();
				playerInfoBuilder.setUid(playerInfo.getUid());
				playerInfoBuilder.setName(playerInfo.getName());
				playerInfoBuilder.setHeader(playerInfo.getHeader());
				playerInfoBuilder.setLevel(playerInfo.getLevel());
				rankInfoBuilder.addPlayerInfoList(playerInfoBuilder);
			}

			msgBuilder.addRankInfoList(rankInfoBuilder);
			count++;
		}
	}

	private void resetLadderRank() {
//		// 重置天梯排行榜
//		LadderOpenEntity entity = Globals.getEntityProxy().get(LadderOpenEntity.class, 1);
//		if (entity == null) {
//			return;
//		}
//
//		String[] cityCodeArray = entity.getOpenArea().split(",");
//		for (RankType rankType : RankType.values()) {
//			if (rankType.getLogicalType() != RankLogicalType.LADDER) {
//				continue;
//			}
//
//			// 清除redis数据
//			clearRankRedisData(rankType);
//
//			// 清除本地缓存数据(必须在清本地缓存之后, 需要读取Map的子榜数据)
//			clearRankLocalData(rankType);
//
//			// 初始化本地缓存数据结构
//			for (String cityCode : cityCodeArray) {
//				Map<String, TreeMap<Integer, RankInfo>> rankDataMap = rankInfoMap.get(rankType);
//				TreeMap<Integer, RankInfo> dataMap = Maps.newTreeMap();
//				rankDataMap.put(cityCode, dataMap);
//			}
//		}
	}

	private void clearRankRedisData(RankType rankType) {
		Map<String, TreeMap<Integer, RankInfo>> map = rankInfoMap.get(rankType);
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			rankType.getLogicalType().getHandler().clearData(rankType, iterator.next());
		}
	}

	private void clearRankLocalData(RankType rankType) {
		if (rankInfoMap.containsKey(rankType)) {
			rankInfoMap.get(rankType).clear();
		}

		if (rankUpdateTimeMap.containsKey(rankType)) {
			rankUpdateTimeMap.get(rankType).clear();
		}
	}

//	private void testInfo(GCRankInfoProto.Builder msgBuilder, RankType rankType, String rankSubType) {
//		for (int i = 0; i < 10; i++) {
//			RankInfoProto.Builder rankInfoBuilder = RankInfoProto.newBuilder();
//			rankInfoBuilder.setRank(i + 1);
//			rankInfoBuilder.setScore(10000 - i);
//			for (int j = 0; j < 2; j++) {
//				PlayerInfoProto.Builder playerInfoBuilder = PlayerInfoProto.newBuilder();
//				playerInfoBuilder.setUid(i + 1);
//				playerInfoBuilder.setName("测试数据" + (j + 1));
//				playerInfoBuilder.setHeader("headerIcon");
//				playerInfoBuilder.setLevel(1);
//				rankInfoBuilder.addPlayerInfoList(playerInfoBuilder);
//			}
//			msgBuilder.addRankInfoList(rankInfoBuilder);
//		}
//	}
}
